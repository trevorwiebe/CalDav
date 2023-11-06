package com.trevorwiebe.caldav.data.model

import java.time.LocalDateTime

data class Event(
    val id: String,
    val url: String,
    val status: String,
    val summary: String,
    val frequency: String,
    val description: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
)
