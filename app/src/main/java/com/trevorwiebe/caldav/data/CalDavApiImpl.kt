package com.trevorwiebe.caldav.data

import com.trevorwiebe.caldav.data.util.Constants
import com.trevorwiebe.caldav.data.util.availableCalendarsRequest
import com.trevorwiebe.caldav.data.util.toFlow
import kotlinx.coroutines.flow.Flow
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

const val TAG = "ApiHelperImpl"
class CalDavApiImpl (
    private val requestBuilder: Request.Builder,
    private val okHttpClient: OkHttpClient
): CalDavApi {

    override suspend fun getCalendars(): Flow<String> {
        val credential = Credentials.basic("testing", "testing")
        val requestBody = availableCalendarsRequest()
        val request = requestBuilder.url(Constants.CALENDAR_BASE_URL + requestBody.url)
            .addHeader("DEPTH", requestBody.depth)
            .addHeader("Content-Type", "application/xml; charset=utf-8")
            .addHeader("Prefer", "return-minimal")
            .method("PROPFIND", requestBody.body.toRequestBody(
                requestBody.body.toMediaTypeOrNull())
            )
            .header("Authorization", credential)
            .build()
        return okHttpClient.newCall(request).toFlow()
    }

}