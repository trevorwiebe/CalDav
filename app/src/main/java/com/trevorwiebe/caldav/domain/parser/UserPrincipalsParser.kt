package com.trevorwiebe.caldav.domain.parser

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class UserPrincipalsParser {

    @Throws(XmlPullParserException::class, IOException::class)
    fun parsePrincipals(data: String): List<String>{
        val principalsList: MutableList<String> = mutableListOf()
        val inputStream = data.byteInputStream()
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)

            var xmlEvent = parser.eventType
            var tag: String?
            var principalLink = false

            while (xmlEvent != XmlPullParser.END_DOCUMENT){
                tag = parser.name
                when (xmlEvent){
                    XmlPullParser.START_TAG -> {
                        when(tag){
                            "d:href" -> principalLink = true
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if(principalLink){
                            principalsList.add(parser.text)
                            principalLink = false
                        }
                    }
                }
                xmlEvent = parser.next()
            }
        }
        return principalsList.toList()
    }
}