package com.trevorwiebe.caldav.domain.mapper

import com.trevorwiebe.caldav.data.auth.AuthCalendar
import com.trevorwiebe.caldav.domain.model.AuthCalendarModel

fun AuthCalendar.toAuthCalendarModel(): AuthCalendarModel {
    return AuthCalendarModel(
        username = username,
        password = password,
        calendarUrl = calendarUrl,
        calendarName = calendarName
    )
}

fun AuthCalendarModel.toAuthCalendar(): AuthCalendar {
    return AuthCalendar(
        username = username,
        password = password,
        calendarUrl = calendarUrl,
        calendarName = calendarName
    )
}