package com.trevorwiebe.caldav.domain.usecases.auth

import com.trevorwiebe.caldav.data.auth.SecurePref
import com.trevorwiebe.caldav.domain.mapper.toAuthCalendar
import com.trevorwiebe.caldav.domain.model.AuthCalendarModel

class SaveAuthCalendar(
    private val securePref: SecurePref
) {
    operator fun invoke(authCalendarList: List<AuthCalendarModel>){
        securePref.saveAuthCalendar(
            authCalendarList = authCalendarList.map { it.toAuthCalendar() }
        )
    }
}