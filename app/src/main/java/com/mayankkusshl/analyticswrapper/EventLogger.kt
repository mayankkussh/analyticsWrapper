package com.mayankkusshl.analyticswrapper

import android.os.Bundle
import android.util.Log

class EventLogger : EventLogContract {

    companion object {
        fun getInstance(): EventLogger {
            return EventLogger()
        }
    }

    private fun printEvent(event: String, propertyBundle: Bundle, tagSuffix: String = "") {
        if (!isDebugMode()) return
        val builder = StringBuilder()
        builder
                .append(event)
                .append(" - ")
        for (key in propertyBundle.keySet())
            builder.append("(")
                    .append(key)
                    .append(", ")
                    .append(propertyBundle.get(key))
                    .append(") ")

        Log.i("Analytics|Event$tagSuffix", builder.toString())

    }

    override fun logEvent(analyticsEvent: AnalyticsEvent) {
        try {
            analyticsEvent.run {
                when(analyticsEvent.type){
                    ActionType.ERROR -> Log.i("Checkpoint", "❌$event")
                    else -> {
                        Log.i("Checkpoint", "✅$event")
                    }
                }
                if (sendToFirebase) {
                    printEvent(event, propertyBundle, "|Firebase  ✅🔥")
                    // Write code to send to firebase
                }

                if (sendToCleverTap) {
                    printEvent(event, propertyBundle, "|Clevertap ✅‍🎓")
                    // Write code to send to clevertap
                }

                if (sendToFacebook) {
                    printEvent(event, propertyBundle, "|Facebook  ✅👤")
                    // Write code to send to facebook
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            //BCrashHelper.logCrash(e)
        }
    }

    override fun isDebugMode(): Boolean {
        // False or true depending upon your config
        return true
    }
}