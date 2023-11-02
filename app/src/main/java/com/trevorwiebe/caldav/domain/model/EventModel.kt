package com.trevorwiebe.caldav.domain.model

import org.joda.time.LocalDate

data class EventModel(
    var id: String?,
    var url: String?,
    var status: String?,
    var summary: String?,
    var frequency: String?,
    var description: String?,
    var startDate: LocalDate?,
    var endDate: LocalDate?,
    var color: String
)
