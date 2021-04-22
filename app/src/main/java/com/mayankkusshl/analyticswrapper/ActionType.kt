package com.mayankkusshl.analyticswrapper

enum class ActionType(val value: String) {
    CLICK("_click"),
    SHOW("_show"),
    SUCCESS("_success"),
    ERROR("_error"),
    SCROLL("_scroll"),
    EXIT("_exit"),
    SWIPE("_swipe"),
    ENTER("_enter"),
    START("start"),
    NONE("")
}