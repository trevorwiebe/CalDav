package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.data.model.Event
import com.trevorwiebe.caldav.domain.parser.CalendarParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCalendars(
    private val calDavApi: CalDavApi,
    private val parser: CalendarParser
) {

    suspend operator fun invoke(
        username: String, password: String, url: String
    ): Flow<List<Event>> {
        return calDavApi.getCalendars(username, password, url)
            .map {
                parser.parseEvents(it)
            }
    }
}