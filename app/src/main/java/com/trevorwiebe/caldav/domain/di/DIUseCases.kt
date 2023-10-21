package com.trevorwiebe.caldav.domain.di

import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.domain.parser.CalendarParser
import com.trevorwiebe.caldav.domain.parser.EventParser
import com.trevorwiebe.caldav.domain.usecases.GetCalendar
import com.trevorwiebe.caldav.domain.usecases.GetEvents
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
    fun provideEventParser(): EventParser{
        return EventParser()
    }

    @Provides
    @ViewModelScoped
    fun provideCalendarParser(): CalendarParser{
        return CalendarParser()
    }

    @Provides
    @ViewModelScoped
    fun provideEvents(
        calDavApi: CalDavApi,
        eventParser: EventParser
    ): GetEvents {
        return GetEvents(calDavApi, eventParser)
    }

    @Provides
    @ViewModelScoped
    fun provideCalendar(
        calDavApi: CalDavApi,
        calendarParser: CalendarParser
    ): GetCalendar {
        return GetCalendar(calDavApi, calendarParser)
    }

}