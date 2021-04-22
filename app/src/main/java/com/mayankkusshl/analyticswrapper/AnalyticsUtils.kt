package com.mayankkusshl.analyticswrapper

import android.os.Bundle


object AnalyticsUtils {
    private const val EVENT_VALUE_LENGTH_LIMIT = 96

    fun getBundleFromMap(map: Map<String, Any>): Bundle {
        return Bundle().apply {
            map.keys.map { key ->
                map[key]?.run {
                    when (this) {
                        is Int -> this@apply.putInt(key, this)
                        is Double -> this@apply.putDouble(key, this)
                        is Float -> this@apply.putFloat(key, this)
                        is Boolean -> this@apply.putBoolean(key, this)
                        is Long -> this@apply.putLong(key, this)
                        else -> this@apply.putString(key, this.toString())
                    }
                }
            }
        }
    }

    fun checkForValueLength(key: String, value: String, bundle: Bundle) {
        try {
            if (value.length < EVENT_VALUE_LENGTH_LIMIT) {
                bundle.putString(key, value)
            } else {
                makeValueList(key, value, bundle)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun makeValueList(key: String, value: String, bundle: Bundle) {
        //Log.d("cxz", "fullString - length : " + value.length() + " : " + value);
        splitStringAndSetInBundle(bundle, key, value, 0, value.length, 0)
    }

    private fun splitStringAndSetInBundle(bundle: Bundle, key: String, value: String, startIndex: Int,
                                          endIndex: Int, counter: Int) {
        var counter = counter
        if (endIndex - startIndex > EVENT_VALUE_LENGTH_LIMIT) {
            val newValue = value.substring(startIndex, startIndex + EVENT_VALUE_LENGTH_LIMIT)
            var lastIndex = EVENT_VALUE_LENGTH_LIMIT
            if (newValue.contains(",")) {
                lastIndex = newValue.lastIndexOf(",")
            } else if (newValue.contains(" ")) {
                lastIndex = newValue.lastIndexOf(" ")
            }
            val substring = value.substring(startIndex, startIndex + lastIndex)
            bundle.putString(key + counter, substring)
            //Log.d("cxz " + counter, "length : " + substring.length() + " " + substring);
            splitStringAndSetInBundle(bundle, key, value, startIndex + lastIndex + 1, endIndex, ++counter)
        } else {
            val substring = value.substring(startIndex)
            //Log.d("cxz " + counter, "length : " + substring.length() + " " + substring);
//
            bundle.putString(key + counter, substring)
        }
    }
}