package com.trevorwiebe.caldav.presentation.calendarEvents

import com.trevorwiebe.caldav.domain.model.EventModel
import org.joda.time.LocalDate

data class DayUi(
    val date: LocalDate,
    val eventList: List<EventModel> = emptyList()
)
