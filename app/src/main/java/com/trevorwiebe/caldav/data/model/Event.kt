package com.trevorwiebe.caldav.data.model

import org.joda.time.LocalDateTime

data class Event(
    var id: String?,
    var url: String?,
    var status: String?,
    var summary: String?,
    var frequency: String?,
    var description: String?,
    var startDate: LocalDate?,
    var endDate: LocalDate?
)
