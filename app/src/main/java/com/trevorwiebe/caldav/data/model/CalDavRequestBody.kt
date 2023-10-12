package com.trevorwiebe.caldav.data.model

data class CalDavRequestBody(
    val body: String,
    val depth: String,
    val method: String
)
