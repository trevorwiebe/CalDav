package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.domain.parser.CalendarLinkParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCalendarLinks(
    val calDavApi: CalDavApi
) {

    private val calendarLinkParser = CalendarLinkParser()

    suspend operator fun invoke(
        username: String, password: String, url: String
    ): Flow<List<String>> {
        return calDavApi.getCalendarLinks(username, password, url)
            .map { calendarLinkParser.parseCalendarLinks(it) }
    }
}