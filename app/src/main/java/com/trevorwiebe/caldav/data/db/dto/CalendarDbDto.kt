package com.trevorwiebe.caldav.data.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "calendar")
data class CalendarDbDto(
    @PrimaryKey
    val primaryKey: Int = 0,
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val timeZone: String = "",
    val syncToken: String = "",
    val order: String = "",
    val color: String = "",
    val numberOfEvents: Int = 0
)
