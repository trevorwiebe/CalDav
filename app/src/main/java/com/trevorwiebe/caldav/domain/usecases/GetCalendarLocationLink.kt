package com.trevorwiebe.caldav.domain.usecases

import com.trevorwiebe.caldav.data.remote.CalDavApi
import com.trevorwiebe.caldav.domain.parser.LocationCalendarLinkParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCalendarLocationLink(
    private val calDavApi: CalDavApi
) {

    private val baseCalendarParser = LocationCalendarLinkParser()

    suspend operator fun invoke(
        username: String, password: String, url: String
    ): Flow<List<String>> {
        return calDavApi.getCalendarLocationLink(username, password, url)
            .map {
                baseCalendarParser.parseLocationCalLink(it)
        }
    }
}