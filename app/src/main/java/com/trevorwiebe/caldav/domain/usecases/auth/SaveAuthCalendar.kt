package com.trevorwiebe.caldav.domain.usecases.auth

import com.trevorwiebe.caldav.data.auth.SecurePref
import com.trevorwiebe.caldav.domain.mapper.toAuthCalendarModel
import com.trevorwiebe.caldav.domain.model.AuthCalendarModel

class SaveAuthCalendar(
    private val securePref: SecurePref
) {
    operator fun invoke(authCalendar: AuthCalendarModel){
        securePref.saveAuthCalendar(
            authCalendar = authCalendar.toAuthCalendarModel()
        )
    }
}