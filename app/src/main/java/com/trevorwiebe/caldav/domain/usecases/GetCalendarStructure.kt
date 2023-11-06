package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.presentation.calendarEvents.DayUi
import java.time.LocalDate

class GetCalendarStructure{

    private val daysToGoForward = 1000
    private val daysToGoBackward = 1000

    operator fun invoke(): List<DayUi>{

        val dateUiList = mutableListOf<DayUi>()
        var currentDate = LocalDate.now().minusDays(daysToGoBackward.toLong())

        repeat(daysToGoBackward) {
            currentDate = currentDate.plusDays(1)
            val dayUi = DayUi(currentDate, emptyList())
            dateUiList.add(dayUi)
        }

        repeat(daysToGoForward){
            currentDate = currentDate.plusDays(1)
            val dayUi = DayUi(currentDate, emptyList())
            dateUiList.add(dayUi)
        }

        val listToRemove = mutableListOf<DayUi>()
        for(dayUi in dateUiList){
            if(dayUi.date.dayOfWeek.value == 7){
                break
            }else{
                listToRemove.add(dayUi)
            }
        }
        return (dateUiList - listToRemove.toSet()).toList()
    }
}