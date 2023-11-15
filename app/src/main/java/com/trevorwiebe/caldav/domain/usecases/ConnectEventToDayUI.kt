package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.presentation.calendarEvents.DayUi

class ConnectEventToDayUI{
    operator fun invoke(eventList: List<EventModel>, dayUiList: List<DayUi>): List<DayUi>{

        val expandedEventList: MutableList<EventModel> = eventList.toMutableList()

        eventList.forEach { event->
            val recurringEventList = GenerateRecurringEventList().generateList(event)
            expandedEventList.addAll(recurringEventList)
        }

        dayUiList.forEach { dayUi ->
            val date = dayUi.date
            dayUi.eventList = expandedEventList.filter {
                it.startDate.toLocalDate() == date
            }
        }

        return dayUiList
    }
}