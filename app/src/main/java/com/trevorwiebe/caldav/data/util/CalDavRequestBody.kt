package com.trevorwiebe.caldav.data.util

data class CalDavRequestBody(
    val body: String,
    val depth: String,
    val method: String
)
