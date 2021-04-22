package com.mayankkusshl.analyticswrapper

interface EventLogContract {
    fun logEvent(event: AnalyticsEvent)
    fun isDebugMode(): Boolean
}