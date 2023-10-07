package com.trevorwiebe.caldav.data.model

data class CalDavRequestBody(
    val body: String,
    val url: String,
    val depth: String
)
