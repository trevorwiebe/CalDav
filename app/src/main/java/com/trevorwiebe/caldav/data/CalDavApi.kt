package com.trevorwiebe.caldav.data

import kotlinx.coroutines.flow.Flow

interface CalDavApi {

    suspend fun getCalendars(
        username: String, password: String, url: String
    ): Flow<String>

}