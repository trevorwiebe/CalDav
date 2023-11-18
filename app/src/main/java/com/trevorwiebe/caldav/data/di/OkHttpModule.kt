package com.trevorwiebe.caldav.data.di

import com.trevorwiebe.caldav.data.remote.CalDavApi
import com.trevorwiebe.caldav.data.remote.CalDavApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Singleton
    @Provides
    fun provideApiHelper(): CalDavApi {
        return CalDavApiImpl()
    }

}