package com.trevorwiebe.caldav.data.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventDbDto(
    @PrimaryKey
    val primaryKey: Int,
    val id: String,
    val url: String,
    val status: String,
    val summary: String,
    val frequency: String,
    val description: String,
    val startDate: String,
    val endDate: String
)
