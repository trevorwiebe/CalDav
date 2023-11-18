package com.trevorwiebe.caldav.data.auth

interface SecurePref {

    fun saveAuthCalendar(authCalendarList: List<AuthCalendar>)

    fun getAuthCalendarList(): List<AuthCalendar>
}