package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.domain.utils.parseLocalDateTime
import java.time.LocalDateTime
import java.time.Period

class GenerateRecurringEventList {

    private lateinit var currentStartDate: LocalDateTime
    private lateinit var currentEndDate: LocalDateTime

    fun generateList(primaryEvent: EventModel): List<EventModel> {

        currentStartDate = primaryEvent.startDate
        currentEndDate = primaryEvent.endDate

        val freqItems = primaryEvent.frequency.split(";")
        val eventList = mutableListOf<EventModel>()
        val maxEndDate = LocalDateTime.now().plusYears(3)

        val recurringObj = createRecurringObj(freqItems.joinToString()) ?: return emptyList()


        if(recurringObj.until is LocalDateTime){

            val dateToStop = recurringObj.until

            while (currentStartDate.isBefore(dateToStop)) {
                val event = createNextEvent(primaryEvent, recurringObj.frequency)
                eventList.add(event)
            }

        } else if (recurringObj.until is Int){

            val count = recurringObj.until

            for(countIndex in 1..count){
                val event = createNextEvent(primaryEvent, recurringObj.frequency)
                eventList.add(event)
            }

        } else {

            while (currentStartDate.isBefore(maxEndDate)) {
                val event = createNextEvent(primaryEvent, recurringObj.frequency)
                eventList.add(event)
            }
        }
        return eventList
    }

    private fun createRecurringObj(recurring: String): RecurringObj? {
        val parts = recurring.split(", ")
        val freq = parts.firstOrNull { it.startsWith("FREQ=") }
        val until = parts.firstOrNull { it.startsWith("UNTIL=") }
        val count = parts.firstOrNull { it.startsWith("COUNT=") }
        val interval = parts.firstOrNull { it.startsWith("INTERVAL=") }
        val byDay = parts.firstOrNull { it.startsWith("BYDAY=") }
        val byMonthDay = parts.firstOrNull { it.startsWith("BYMONTHDAY=") }

        val period = getPeriod(freq)
        val untilDateOrCount = getUntil(until, count)

        return if(period != null) {
            RecurringObj(period, untilDateOrCount)
        }else {
            null
        }
    }

    private fun createNextEvent(
        event: EventModel,
        period: Period
    ): EventModel{
        currentStartDate = currentStartDate.plus(period)
        currentEndDate = currentEndDate.plus(period)

        return event.copy(
            startDate = currentStartDate,
            endDate = currentEndDate
        )
    }

    private fun getPeriod(freq: String?): Period? {
        return when (freq) {
            "FREQ=YEARLY" -> Period.ofYears(1)
            "FREQ=MONTHLY" -> Period.ofMonths(1)
            "FREQ=WEEKLY" -> Period.ofWeeks(1)
            "FREQ=DAILY" -> Period.ofDays(1)
            else -> null
        }
    }

    private fun getUntil(dateStr: String?, count: String?): Any?{
        return if(dateStr!=null){
            val dateString = dateStr.split("=")
            parseLocalDateTime(dateString[1])
        }else if(count!=null){
            val countString = count.split("=")
            countString[1].toInt()
        }else{
            null
        }
    }

    data class RecurringObj(
        val frequency: Period,
        val until: Any?
    )

}