package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.domain.mapper.toEventModel
import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.domain.parser.EventParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetEvents(
    private val calDavApi: CalDavApi,
    private val parser: EventParser
) {

    suspend operator fun invoke(
        username: String, password: String, url: String
    ): Flow<List<EventModel>> {
        return calDavApi.getEvents(username, password, url)
            .map { parser.parseEvents(it).map { it.toEventModel() } }
    }
}