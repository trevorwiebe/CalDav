package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class CalendarLinkParser {

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseCalendarLinks(data: String): List<String> {
        val calendarLinks: MutableList<String> = mutableListOf()
        val inputStream = data.byteInputStream()
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)

            var xmlEvent = parser.eventType
            var calLink = false

            while(xmlEvent != XmlPullParser.END_DOCUMENT){
                when (xmlEvent){
                    XmlPullParser.START_TAG -> {
                        if(parser.name == ""){
                            calLink = true
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if(calLink){
                            val link = parser.text
                            calendarLinks.add(link)
                            calLink = false
                        }
                    }
                }
                xmlEvent = parser.next()
            }
        }
        return emptyList()
    }

}