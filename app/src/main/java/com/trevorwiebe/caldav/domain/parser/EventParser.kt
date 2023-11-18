package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import com.trevorwiebe.caldav.domain.eventModel
import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.domain.utils.parseLocalDateTime
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class EventParser{

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseEvents(data: String): List<EventModel> {
        val inputStream = data.byteInputStream()
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            return parseEventsHelper(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parseEventsHelper(parser: XmlPullParser): List<EventModel> {

        val eventList = mutableListOf<EventModel>()

        var event = eventModel()

        var tag: String?
        var xmlEvent = parser.eventType

        var href = false
        var etag = false
        var status = false
        var calendarDataBool = false

        while (xmlEvent != XmlPullParser.END_DOCUMENT) {
            tag = parser.name
            when (xmlEvent) {
                XmlPullParser.START_TAG ->  {
                    when(tag){
                        "d:href" -> href = true
                        "d:getetag" -> etag = true
                        "cal:calendar-data" -> calendarDataBool = true
                        "d:status" -> status = true
                    }
                }
                XmlPullParser.TEXT -> {
                    if(href){
                        event = event.copy(url = parser.text)
                        href = false
                    }
                    if(etag){
                        event = event.copy(id = parser.text)
                        etag = false
                    }
                    if(calendarDataBool){
                        event = parseICSCalendar(event, parser.text)
                        calendarDataBool = false
                    }
                    if(status){
                        event = event.copy(status = parser.text)
                        status = false
                        eventList.add(event)

                        event = eventModel()
                    }
                }
            }
            xmlEvent = parser.next()
        }
        return eventList.toList()
    }

    private fun parseICSCalendar(currentEvent: EventModel, calendarData: String): EventModel {

        val lines = calendarData.split("\n")

        try {
            val eventLines = mutableListOf<String>()
            var inEvent = false

            for (line in lines) {
                if (line.startsWith("BEGIN:VEVENT")) {
                    inEvent = true
                    eventLines.clear()
                } else if (line.startsWith("END:VEVENT") && inEvent) {
                    return parseEvent(currentEvent, eventLines)
                } else if (inEvent) {
                    eventLines.add(line)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return currentEvent
    }

    private fun parseEvent(event: EventModel, eventLines: List<String>): EventModel {

        var currentEvent = event

        for (line in eventLines) {
            val parts = line.split(":")
            if (parts.size == 2) {
                if(parts[0].split(";").first() == "DTSTART"){
                    currentEvent = currentEvent.copy(startDate = parseLocalDateTime(parts[1]))
                }else if(parts[0].split(";").first() == "DTEND"){
                    currentEvent = currentEvent.copy(endDate = parseLocalDateTime(parts[1]))
                }
                when (parts[0]) {
                    "SUMMARY" -> currentEvent = currentEvent.copy(summary = parts[1])
                    "DESCRIPTION" -> currentEvent = currentEvent.copy(description = parts[1])
                    "RRULE" -> currentEvent = currentEvent.copy(frequency = parts[1])
                }
            }
        }
        return currentEvent
    }
}