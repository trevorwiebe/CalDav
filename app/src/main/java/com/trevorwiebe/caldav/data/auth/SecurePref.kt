package com.trevorwiebe.caldav.data.auth

interface SecurePref {

    fun saveAuthCalendar(authCalendar: AuthCalendar)

    fun getAuthCalendarList(): List<AuthCalendar>
}