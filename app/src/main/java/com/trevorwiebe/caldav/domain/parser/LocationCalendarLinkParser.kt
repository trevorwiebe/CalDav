package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class LocationCalendarLinkParser {

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseLocationCalLink(data: String): List<String>{
        val locationLinkList: MutableList<String> = mutableListOf()
        val inputStream = data.byteInputStream()
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)

            var xmlEvent = parser.eventType
            var tag: String?
            var link = false

            while (xmlEvent != XmlPullParser.END_DOCUMENT){
                tag = parser.name
                when(xmlEvent){
                    XmlPullParser.START_TAG -> {
                        if(tag == "d:href"){
                            link = true
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if(link){
                            locationLinkList.add(parser.text)
                            link = false
                        }
                    }
                }

                xmlEvent = parser.next()
            }
        }
        return locationLinkList.toList()
    }
}