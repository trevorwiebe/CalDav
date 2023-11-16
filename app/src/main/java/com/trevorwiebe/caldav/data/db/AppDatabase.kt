package com.trevorwiebe.caldav.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trevorwiebe.caldav.data.db.dto.CalendarDbDto
import com.trevorwiebe.caldav.data.db.dto.EventDbDto

@Database(
    entities = [CalendarDbDto::class, EventDbDto::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

}