package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import com.trevorwiebe.caldav.data.model.Calendar
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class CalendarParser {

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseCalendar(data: String): Calendar? {
        val inputStream = data.byteInputStream()
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            return parseCalendarHelper(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseCalendarHelper(parser: XmlPullParser): Calendar? {
        var calendar = Calendar("", "", "", "", emptyList(), "", "", "")
        var xmlEvent = parser.eventType
        var tag: String?
        var title = false
        var url = false
        var description = false
        var timeZone = false
        var supportedComponentSet = false
        var syncToken = false
        var order = false
        var color = false
        var numberOfEvents = 0

        while (xmlEvent != XmlPullParser.END_DOCUMENT){
            tag = parser.name
            when (xmlEvent){
                XmlPullParser.START_TAG -> {
                    when(tag){
                        "d:href" -> url = true
                        "s:sync-token" -> syncToken = true
                        "d:displayname" -> title = true
                        "cal:calendar-description" -> description = true
                        "cal:calendar-timezone" -> timeZone = true
                        "x1:calendar-order" -> order = true
                        "x1:calendar-color" -> color = true
                        "d:response" -> numberOfEvents++
                    }
                }
                XmlPullParser.TEXT -> {
                    if(url){
                        calendar = calendar.copy(url = parser.text)
                        url = false
                    }
                    if(syncToken){
                        calendar = calendar.copy(syncToken = parser.text)
                        syncToken = false
                    }
                    if(title){
                        calendar = calendar.copy(title = parser.text)
                        title = false
                    }
                    if(description){
                        calendar = calendar.copy(description = parser.text)
                        description = false
                    }
                    if(timeZone){
                        calendar = calendar.copy(timeZone = parser.text)
                        timeZone = false
                    }
                    if(order){
                        calendar = calendar.copy(order = parser.text)
                        order = false
                    }
                    if(color){
                        calendar = calendar.copy(color = parser.text)
                        color = false
                    }
                }
                XmlPullParser.END_TAG -> {
                    // TODO: fix this issue where the parser stops after the first 'd:response'
                    if(tag == "d:response"){
                        calendar = calendar.copy(numberOfEvents = numberOfEvents)
                        return calendar
                    }
                }
            }
            xmlEvent = parser.next()
        }
        return null
    }
}