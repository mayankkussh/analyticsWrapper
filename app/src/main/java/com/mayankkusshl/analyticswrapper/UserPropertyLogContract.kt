package com.mayankkusshl.analyticswrapper

import android.os.Bundle

interface UserPropertyLogContract {

    fun updateUserProperty(propertyName: String, value: Any)

    fun updateUserProperty(properties: Map<String, Any>)

    fun setClevertapProperties(map: Map<String, Any>)

    fun setFirebaseProperties(map: Map<String, Any>)

    fun setFacebookProperties(bundle: Bundle)

    fun isDebugMode(): Boolean
}