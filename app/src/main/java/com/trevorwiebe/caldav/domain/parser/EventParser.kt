package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import com.trevorwiebe.caldav.data.model.Event
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
class EventParser{

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseEvents(data: String): List<Event> {
        val inputStream = data.byteInputStream()
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            return parseEventsHelper(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parseEventsHelper(parser: XmlPullParser): List<Event> {

        val eventList = mutableListOf<Event>()

        var event = Event("", "", "", "", "", "", null, null)

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
                        event.url = parser.text
                        href = false
                    }
                    if(etag){
                        event.id = parser.text
                        etag = false
                    }
                    if(calendarDataBool){
                        event = parseICSCalendar(event, parser.text)
                        calendarDataBool = false
                    }
                    if(status){
                        event.status = parser.text
                        status = false
                        eventList.add(event)

                        event = Event("", "", "", "", "", "", null, null)
                    }
                }
            }
            xmlEvent = parser.next()
        }
        return eventList.toList()
    }

    private fun parseICSCalendar(currentEvent: Event, calendarData: String): Event {

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

    private fun parseEvent(currentEvent: Event, eventLines: List<String>): Event {

        for (line in eventLines) {
            val parts = line.split(":")
            if (parts.size == 2) {
                if(parts[0].split(";").first() == "DTSTART"){
                    currentEvent.startDate = parseDate(parts[1])
                }else if(parts[0].split(";").first() == "DTEND"){
                    currentEvent.endDate = parseDate(parts[1])
                }
                when (parts[0]) {
                    "SUMMARY" -> currentEvent.summary = parts[1]
                    "DESCRIPTION" -> currentEvent.description = parts[1]
                    "RRULE-FREQ" -> currentEvent.frequency = parts[1]
                }
            }
        }
        return currentEvent
    }
    private fun parseDate(dateString: String): LocalDateTime {
        val pattern = if(dateString.length == 8){
            "yyyyMMdd"
        }else{
            "yyyyMMdd'T'HHmmss"
        }
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormat.forPattern(pattern)
        val date = LocalDateTime.parse(dateString, dateTimeFormatter)
        return date
    }
}