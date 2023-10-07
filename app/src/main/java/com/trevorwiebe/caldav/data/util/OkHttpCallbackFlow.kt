package com.trevorwiebe.caldav.data.util

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

fun Call.toFlow(): Flow<String> =
    callbackFlow {
    val callback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            trySend(e.toString())
        }

        override fun onResponse(call: Call, response: Response) {
            trySend(response.body?.string()?:"")
        }
    }
    enqueue(callback)
    awaitClose{cancel()}
}