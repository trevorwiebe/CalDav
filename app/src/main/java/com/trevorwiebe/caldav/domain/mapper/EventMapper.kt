package com.trevorwiebe.caldav.domain.mapper

import com.trevorwiebe.caldav.data.model.Event
import com.trevorwiebe.caldav.domain.model.EventModel

fun Event.toEventModel(color: String): EventModel {
    return EventModel(
        id = id,
        url = url,
        status = status,
        summary = summary,
        frequency = frequency,
        description = description,
        startDate = startDate,
        endDate = endDate,
        color = color
    )
}