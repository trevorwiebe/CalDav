package com.trevorwiebe.caldav.domain.model

import com.trevorwiebe.caldav.data.model.CalComponentSet

data class CalendarModel(
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val timeZone: String = "",
    val supportedComponentSet: List<CalComponentSet>,
    val syncToken: String = "",
    val order: String = "",
    val color: String = "",
    val numberOfEvents: Int = 0
)
