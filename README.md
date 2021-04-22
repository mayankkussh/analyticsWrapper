# analyticsWrapper

### Create singleton instance of app analytics for app wide usage using any DI platform or anyhow you prefer
private val appAnalytics = AppAnalytics(EventLogger(), UserPropertyLogger())

### Create an event
```
 // Add name of event and type of event
    val event = AnalyticsEvent.newEvent("my_custom event", ActionType.ENTER)
        .put("key1", "value1")
        // Add map of properties
        .put(HashMap())
        // Add bundle of properties
        .put(Bundle())
        // Enable or disable specific platform for this event
        .disableClevertap()
```            
### Send event
appAnalytics.logEvent(event)

### Send User Properties
appAnalytics.setClevertapProperties()
