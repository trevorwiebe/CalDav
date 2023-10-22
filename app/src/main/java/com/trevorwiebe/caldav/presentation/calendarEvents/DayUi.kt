package com.trevorwiebe.caldav.presentation.calendarEvents

import com.trevorwiebe.caldav.domain.model.EventModel

data class DayUi(
    val date: Long = 0L,
    val eventList: List<EventModel> = emptyList()
)
