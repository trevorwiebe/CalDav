package com.trevorwiebe.caldav.domain.di

import com.trevorwiebe.caldav.data.CalDavApi
import com.trevorwiebe.caldav.data.auth.SecurePref
import com.trevorwiebe.caldav.domain.parser.CalendarParser
import com.trevorwiebe.caldav.domain.parser.EventParser
import com.trevorwiebe.caldav.domain.usecases.ConnectEventToDayUI
import com.trevorwiebe.caldav.domain.usecases.GetCalendar
import com.trevorwiebe.caldav.domain.usecases.GetCalendarLocationLink
import com.trevorwiebe.caldav.domain.usecases.GetCalendarLinks
import com.trevorwiebe.caldav.domain.usecases.GetEvents
import com.trevorwiebe.caldav.domain.usecases.GetCalendarStructure
import com.trevorwiebe.caldav.domain.usecases.GetUserPrincipals
import com.trevorwiebe.caldav.domain.usecases.LoadAvailableCalendars
import com.trevorwiebe.caldav.domain.usecases.auth.GetAuthUserList
import com.trevorwiebe.caldav.domain.usecases.auth.SaveAuthUser
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
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
    fun provideGetCalendarStructure(): GetCalendarStructure{
        return GetCalendarStructure()
    }

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
    fun provideConnectEventToDayUi(): ConnectEventToDayUI{
        return ConnectEventToDayUI()
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

    @Provides
    @ViewModelScoped
    fun provideSaveUser(
        securePref: SecurePref
    ): SaveAuthUser{
        return SaveAuthUser(securePref)
    }

    @Provides
    @ViewModelScoped
    fun provideGetUser(
        securePref: SecurePref
    ): GetAuthUserList {
        return GetAuthUserList(securePref)
    }

    @Provides
    @ViewModelScoped
    fun provideUserAuthentication(
        saveAuthUser: SaveAuthUser,
        getAuthUserList: GetAuthUserList
    ): UserAuthentication{
        return UserAuthentication(
            saveAuthUser = saveAuthUser,
            getAuthUserList = getAuthUserList,
        )
    }

    @Provides
    @ViewModelScoped
    fun providesGetUserPrincipals(
        calDavApi: CalDavApi
    ): GetUserPrincipals {
        return GetUserPrincipals(calDavApi)
    }

    @Provides
    @ViewModelScoped
    fun provideCalBaseLink(
        calDavApi: CalDavApi
    ): GetCalendarLocationLink {
        return GetCalendarLocationLink(calDavApi)
    }

    @Provides
    @ViewModelScoped
    fun provideCalLinks(
        calDavApi: CalDavApi
    ): GetCalendarLinks {
        return GetCalendarLinks(calDavApi)
    }

    @Provides
    @ViewModelScoped
    fun provideLoadAvailableCalendars(
        getUserPrincipals: GetUserPrincipals,
        getCalendarLocationLink: GetCalendarLocationLink,
        getCalendarLinks: GetCalendarLinks
    ): LoadAvailableCalendars {
        return LoadAvailableCalendars(
            getUserPrincipals = getUserPrincipals,
            getCalendarLocationLink = getCalendarLocationLink,
            getCalendarLinks = getCalendarLinks
        )
    }

}