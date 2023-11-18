package com.trevorwiebe.caldav.domain.usecases.auth

import com.trevorwiebe.caldav.data.auth.SecurePref
import com.trevorwiebe.caldav.domain.mapper.toAuthCalendarModel
import com.trevorwiebe.caldav.domain.model.AuthCalendarModel

class GetAuthCalendarList(
    private val securePref: SecurePref
) {

    operator fun invoke(): List<AuthCalendarModel> {
        return securePref.getAuthCalendarList()
            .map { it.toAuthCalendarModel() }
    }
}