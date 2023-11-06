package com.trevorwiebe.caldav.domain.model

import java.time.LocalDateTime

data class EventModel(
    var id: String?,
    var url: String?,
    var status: String?,
    var summary: String?,
    var frequency: String?,
    var description: String?,
    var startDate: LocalDateTime?,
    var endDate: LocalDateTime?,
    var color: String
)
