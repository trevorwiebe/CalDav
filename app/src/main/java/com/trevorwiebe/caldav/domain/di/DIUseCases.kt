package com.trevorwiebe.caldav.domain.di

import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.domain.parser.CalendarParser
import com.trevorwiebe.caldav.domain.usecases.GetCalendars
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DIUseCases {

    @Provides
    @ViewModelScoped
    fun provideCalendarParser(): CalendarParser{
        return CalendarParser()
    }

    @Provides
    @ViewModelScoped
    fun provideEvents(
        calDavApi: CalDavApi,
        calendarParser: CalendarParser
    ): GetCalendars {
        return GetCalendars(calDavApi, calendarParser)
    }

}