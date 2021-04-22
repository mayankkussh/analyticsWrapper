package com.mayankkusshl.analyticswrapper

import android.os.Bundle

class AnalyticsEvent private constructor() {
    val propertyBundle: Bundle = Bundle()
    val propertyMap = HashMap<String, Any>()
    var sendToCleverTap = true
    var sendToFirebase = true
    var sendToFacebook = true

    lateinit var type: ActionType
    lateinit var event: String

    companion object {
        fun newEvent(event: String, type: ActionType): AnalyticsEvent {
            return AnalyticsEvent().init(event, type)
        }
    }

    fun disableClevertap(): AnalyticsEvent {
        sendToCleverTap = false
        return this
    }

    fun disableFirebase(): AnalyticsEvent {
        sendToFirebase = false
        return this
    }

    fun disableFacebook(): AnalyticsEvent {
        sendToFacebook = false
        return this
    }


    private fun init(event: String, type: ActionType): AnalyticsEvent {
        this.event = event
        this.type = type
        return this
    }


    fun put(key: String, value: Any?): AnalyticsEvent {
        if (value != null) {
            when (value) {
                is Int -> propertyBundle.putInt(key, value)
                is Double -> propertyBundle.putDouble(key, value)
                is Float -> propertyBundle.putFloat(key, value)
                is Boolean -> propertyBundle.putBoolean(key, value)
                is Long -> propertyBundle.putLong(key, value)
                else -> propertyBundle.putString(key, value.toString())
            }
            propertyMap[key] = value
        }
        return this
    }

    fun put(map: Map<String, Any>): AnalyticsEvent {
        propertyMap.putAll(map)
        map.keys.forEach {
            put(it, map[it])
        }
        return this
    }

    fun put(bundle: Bundle): AnalyticsEvent {
        propertyBundle.putAll(bundle)
        bundle.keySet().forEach {
            propertyMap[it] = bundle.get(it)!!
        }
        return this
    }
}

