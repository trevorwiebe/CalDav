package com.trevorwiebe.caldav.presentation.calendarEvents

import com.trevorwiebe.caldav.domain.model.EventModel
import java.time.LocalDate

data class DayUi(
    val date: LocalDate,
    var eventList: List<EventModel> = emptyList()
)
