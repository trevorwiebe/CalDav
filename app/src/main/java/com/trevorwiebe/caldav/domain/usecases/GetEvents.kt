package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.data.remote.CalDavApi
import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.domain.parser.EventParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetEvents(
    private val calDavApi: CalDavApi,
    private val parser: EventParser
) {

    suspend operator fun invoke(
        username: String, password: String, url: String, eventColor: String
    ): Flow<List<EventModel>> {
        return calDavApi.getEvents(username, password, url)
            .map {
                parser.parseEvents(it).map { it.copy(color = eventColor) }
            }
    }
}