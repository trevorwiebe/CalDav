package com.trevorwiebe.caldav.domain.model

data class EventModel(
    val title: String = "",
    var id: String?,
    var url: String?,
    var status: String?,
    var calendarData: String?
)
