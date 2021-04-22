package com.mayankkusshl.analyticswrapper

import android.os.Bundle

class AppAnalytics (private val eventLogContract: EventLogContract,
                    private val userPropertyLogContract: UserPropertyLogContract){


    fun setFacebookProperties() {
        //Set your properties specific to facebook here
        //userPropertyLogContract.setFacebookProperties(userPropertiesFactory.getFacebookProperties())
    }

    fun setClevertapProperties() {
        //Set your properties specific to clevetap here
        //userPropertyLogContract.setClevertapProperties(userPropertiesFactory.getClevertapProperties())
    }

    fun setFirebaseProperties() {
        //Set your properties specific to firebase here
        //userPropertyLogContract.setFirebaseProperties(userPropertiesFactory.getFirebaseProperties())
    }

    fun logEvent(analyticsEvent: AnalyticsEvent) {
        analyticsEvent.put(addCommonProperties(analyticsEvent.propertyMap))
        eventLogContract.logEvent(analyticsEvent)
    }

    private fun addCommonProperties(propertyMap: Map<String, Any>): Bundle {
        // Add common properties for each event here
        return Bundle()
    }

}