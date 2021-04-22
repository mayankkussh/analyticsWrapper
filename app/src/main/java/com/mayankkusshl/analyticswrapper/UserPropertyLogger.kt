package com.mayankkusshl.analyticswrapper

import android.os.Bundle
import android.util.Log

class UserPropertyLogger   : UserPropertyLogContract {

    companion object {
        private const val FACEBOOK = "Analytics|UserProperty|Facebook"
        private const val CLEVERTAP = "Analytics|UserProperty|Clevertap"
        private const val FIREBASE = "Analytics|UserProperty|Firebase"

        fun getInstance(): UserPropertyLogger {
            return UserPropertyLogger()
        }
    }

    override fun updateUserProperty(propertyName: String, value: Any) {
        val map = HashMap<String, Any>().apply {
            put(propertyName, value)
        }
        updateUserProperty(map)
    }

    override fun updateUserProperty(properties: Map<String, Any>) {
        setClevertapProperties(properties)
        setFirebaseProperties(properties)
        AnalyticsUtils.getBundleFromMap(properties).run {
            setFacebookProperties(this)
        }
    }

    override fun setClevertapProperties(map: Map<String, Any>) {
        if (isDebugMode()) {
            print(CLEVERTAP, map)
        }
        // Send properties to clevertap
    }

    override fun setFirebaseProperties(map: Map<String, Any>) {
        if (isDebugMode()) {
            print(FIREBASE, map)
        }
       // Send Properties for firebase
    }

    override fun setFacebookProperties(bundle: Bundle) {
        if (isDebugMode()) {
            print(FACEBOOK, bundle)
        }
        //Send Properties for facebook
    }

    override fun isDebugMode(): Boolean {
        // true or false depending upon the requirements
        return true
    }

    private fun print(tag: String, properties: Map<String, Any>) {
        val builder = StringBuilder()
        for (key in properties.keys)
            builder.append("(").append(key).append(", ").append(properties.get(key)).append(") ")
        Log.i(tag, builder.toString())
    }

    private fun print(tag: String, properties: Bundle) {
        val builder = StringBuilder()
        for (key in properties.keySet())
            builder.append("(").append(key).append(", ").append(properties.get(key)).append(") ")
        Log.i(tag, builder.toString())
    }
}

