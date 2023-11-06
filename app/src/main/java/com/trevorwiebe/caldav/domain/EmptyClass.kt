package com.trevorwiebe.caldav.domain

import com.trevorwiebe.caldav.data.model.Event
import com.trevorwiebe.caldav.domain.model.EventModel
import java.time.LocalDateTime

fun eventModel(): EventModel{
    return EventModel(
        "",
        "",
        "",
        "",
        "",
        "",
        LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        ""
    )
}

fun event(): Event{
    return Event (
        "",
        "",
        "",
        "",
        "",
        "",
        LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        LocalDateTime.of(1900, 1, 1, 0, 0, 0),
    )
}