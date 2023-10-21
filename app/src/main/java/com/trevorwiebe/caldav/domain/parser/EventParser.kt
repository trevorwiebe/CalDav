package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import com.trevorwiebe.caldav.data.model.Event
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

const val TAG = "CalendarParser"
class EventParser {

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

        var event = Event("", "", "", "")

        var tag: String?
        var xmlEvent = parser.eventType

        var href = false
        var etag = false
        var status = false
        var calendarData = false

        while (xmlEvent != XmlPullParser.END_DOCUMENT) {
            tag = parser.name
            when (xmlEvent) {
                XmlPullParser.START_TAG ->  {
                    when(tag){
                        "d:href" -> href = true
                        "d:getetag" -> etag = true
                        "cal:calendar-data" -> calendarData = true
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
                    if(calendarData){
                        event.calendarData = parser.text
                        calendarData = false
                    }
                    if(status){
                        event.status = parser.text
                        status = false
                        val eventToAdd = event
                        eventList.add(eventToAdd)
                        event = Event("", "", "", "")
                    }
                }
            }
            xmlEvent = parser.next()
        }
        return eventList.toList()
    }
}