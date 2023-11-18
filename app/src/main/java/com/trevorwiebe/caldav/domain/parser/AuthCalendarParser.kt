package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import com.trevorwiebe.caldav.domain.model.AuthCalendarModel
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class AuthCalendarParser {

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseCalendarLinks(data: String): List<AuthCalendarModel> {
        val authCalendarModelList: MutableList<AuthCalendarModel> = mutableListOf()
        val inputStream = data.byteInputStream()
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)

            var xmlEvent = parser.eventType
            var calLink = false
            var calName = false

            var authCalendarModel = AuthCalendarModel("", "", "", "")

            while(xmlEvent != XmlPullParser.END_DOCUMENT){
                when (xmlEvent){
                    XmlPullParser.START_TAG -> {
                        when(parser.name){
                            "d:href" -> calLink = true
                            "d:displayname" -> calName = true
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if(calLink){
                            val link = parser.text
                            authCalendarModel = authCalendarModel.copy(calendarUrl = link)
                            calLink = false
                        }
                        if(calName){
                            val name = parser.text
                            authCalendarModel = authCalendarModel.copy(calendarName = name)
                            calName = false
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if(parser.name == "d:response"){
                            authCalendarModelList.add(authCalendarModel)
                            authCalendarModel = AuthCalendarModel("", "", "", "")
                        }
                    }
                }
                xmlEvent = parser.next()
            }
        }
        return authCalendarModelList
    }

}