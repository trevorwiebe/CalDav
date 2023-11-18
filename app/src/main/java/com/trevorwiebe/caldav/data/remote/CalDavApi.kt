package com.trevorwiebe.caldav.data.remote

import kotlinx.coroutines.flow.Flow

interface CalDavApi {

    suspend fun getCalendar(
        username: String, password: String, url: String
    ): Flow<String>
    suspend fun getEvents(
        username: String, password: String, url: String
    ): Flow<String>
    suspend fun getPrincipals(
        username: String, password: String, url: String
    ): Flow<String>
    suspend fun getCalendarLocationLink(
        username: String, password: String, url: String
    ): Flow<String>
    suspend fun getCalendarLinks(
        username: String, password: String, url: String
    ): Flow<String>

}