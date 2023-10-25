package com.trevorwiebe.caldav.data.model

import org.joda.time.LocalDate

data class Event(
    var id: String?,
    var url: String?,
    var status: String?,
    var summary: String?,
    var description: String?,
    var startDate: LocalDate?,
    var endDate: LocalDate?
)
