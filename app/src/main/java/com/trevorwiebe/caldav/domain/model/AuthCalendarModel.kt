package com.trevorwiebe.caldav.domain.model

data class AuthCalendarModel(
    val username: String,
    val password: String,
    val calendarUrl: String,
    val calendarName: String
)
