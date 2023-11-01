package com.trevorwiebe.caldav.domain.mapper

import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.domain.model.CalendarModel

fun Calendar.toCalendarModel(): CalendarModel{
    return CalendarModel(
        title = title,
        description = description,
        url = url,
        timeZone = timeZone,
        supportedComponentSet = supportedComponentSet,
        syncToken = syncToken,
        order = order,
        color = color,
        numberOfEvents = numberOfEvents
    )
}