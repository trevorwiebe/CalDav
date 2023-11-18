package com.trevorwiebe.caldav.domain.usecases.auth

data class CalendarAuthentication(
    val saveAuthCalendar: SaveAuthCalendar,
    val getAuthCalendarList: GetAuthCalendarList
)
