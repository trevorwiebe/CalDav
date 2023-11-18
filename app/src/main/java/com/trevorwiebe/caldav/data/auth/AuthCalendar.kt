package com.trevorwiebe.caldav.data.auth

data class AuthCalendar(
    val username: String,
    val password: String,
    val calendarUrl: String,
    val calendarName: String
)
