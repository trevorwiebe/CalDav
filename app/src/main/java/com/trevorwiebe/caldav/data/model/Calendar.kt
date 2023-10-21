package com.trevorwiebe.caldav.data.model

data class Calendar(
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val timeZone: String = "",
    val supportedComponentSet: List<CalComponentSet>,
    val syncToken: String = "",
    val order: String = "",
    val color: String = ""
)
