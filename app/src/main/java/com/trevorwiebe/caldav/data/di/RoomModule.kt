package com.trevorwiebe.caldav.data.di

import android.app.Application
import androidx.room.Room
import com.trevorwiebe.caldav.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java, "event_flow_db"
        ).build()
    }
}