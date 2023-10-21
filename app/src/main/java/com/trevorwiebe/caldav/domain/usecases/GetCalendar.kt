package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.domain.parser.CalendarParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCalendar(
    private val calDavApi: CalDavApi,
    private val calendarParser: CalendarParser
) {

    suspend operator fun invoke(
        username: String, password: String, url: String
    ): Flow<List<Calendar>> {
        return calDavApi.getCalendar(username, password, url)
            .map { calendarParser.parseCalendar(it) }
    }
}