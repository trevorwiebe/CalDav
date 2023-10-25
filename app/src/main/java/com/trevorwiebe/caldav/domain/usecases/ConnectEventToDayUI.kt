package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.presentation.calendarEvents.DayUi

class ConnectEventToDayUI{
    operator fun invoke(eventList: List<EventModel>, dayUiList: List<DayUi>): List<DayUi>{

        dayUiList.forEach { dayUi ->
            val date = dayUi.date
            dayUi.eventList = eventList.filter { it.startDate == date }
        }

        return dayUiList
    }
}