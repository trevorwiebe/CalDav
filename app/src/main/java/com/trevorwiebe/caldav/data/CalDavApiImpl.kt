package com.trevorwiebe.caldav.data

import com.trevorwiebe.caldav.BuildConfig
import com.trevorwiebe.caldav.data.model.CalDavRequestBody
import com.trevorwiebe.caldav.data.util.getCalendarLinksRequest
import com.trevorwiebe.caldav.data.util.getCalendarLocationLinkRequest
import com.trevorwiebe.caldav.data.util.getCalendarRequest
import com.trevorwiebe.caldav.data.util.getEventsRequest
import com.trevorwiebe.caldav.data.util.getUserPrincipalsRequest
import com.trevorwiebe.caldav.data.util.toFlow
import kotlinx.coroutines.flow.Flow
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor

class CalDavApiImpl: CalDavApi {

    override suspend fun getCalendar(
        username: String,
        password: String,
        url: String
    ): Flow<String> {
        val credential = Credentials.basic(username, password)
        val requestBody = getCalendarRequest()
        val request = generateRequest(credential, requestBody, url)
        return getOkHttp().newCall(request).toFlow()
    }

    override suspend fun getEvents(
        username: String, password: String, url: String
    ): Flow<String> {
        val credential = Credentials.basic(username, password)
        val requestBody = getEventsRequest()
        val request = generateRequest(credential, requestBody, url)
        return getOkHttp().newCall(request).toFlow()
    }

    override suspend fun getPrincipals(
        username: String,
        password: String,
        url: String
    ): Flow<String> {
        val credential = Credentials.basic(username, password)
        val requestBody = getUserPrincipalsRequest()
        val request = generateRequest(credential, requestBody, url)
        return getOkHttp().newCall(request).toFlow()
    }

    override suspend fun getCalendarLocationLink(
        username: String,
        password: String,
        url: String
    ): Flow<String> {
        val credential = Credentials.basic(username, password)
        val requestBody = getCalendarLocationLinkRequest()
        val request = generateRequest(credential, requestBody, url)
        return getOkHttp().newCall(request).toFlow()
    }

    override suspend fun getCalendarLinks(
        username: String,
        password: String,
        url: String
    ): Flow<String> {
        val credential = Credentials.basic(username, password)
        val requestBody = getCalendarLinksRequest()
        val request = generateRequest(credential, requestBody, url)
        return getOkHttp().newCall(request).toFlow()
    }

    private fun generateRequest(
        credential: String,
        requestBody: CalDavRequestBody,
        url: String
    ): Request{
        return Request.Builder().url(url)
            .addHeader("DEPTH", requestBody.depth)
            .addHeader("Content-Type", "application/xml; charset=utf-8")
            .addHeader("Prefer", "return-minimal")
            .method(requestBody.method, requestBody.body.toRequestBody(
                requestBody.body.toMediaTypeOrNull())
            )
            .header("Authorization", credential)
            .build()
    }

    private fun getOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return if(BuildConfig.DEBUG){
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }else{
            OkHttpClient.Builder()
                .build()
        }
    }

}