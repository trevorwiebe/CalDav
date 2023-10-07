package com.trevorwiebe.caldav.data

import kotlinx.coroutines.flow.Flow

interface CalDavApi {

    suspend fun getCalendars(): Flow<String>

}