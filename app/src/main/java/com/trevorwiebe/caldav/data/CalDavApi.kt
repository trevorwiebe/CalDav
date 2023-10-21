package com.trevorwiebe.caldav.data

import kotlinx.coroutines.flow.Flow

interface CalDavApi {

    suspend fun getCalendar(
        username: String, password: String, url: String
    ): Flow<String>
    suspend fun getEvents(
        username: String, password: String, url: String
    ): Flow<String>

}