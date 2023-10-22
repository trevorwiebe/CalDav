package com.trevorwiebe.caldav.domain.usecases.auth

import com.trevorwiebe.caldav.data.auth.SecurePref
import com.trevorwiebe.caldav.domain.mapper.toAuthUserModel
import com.trevorwiebe.caldav.domain.model.AuthUserModel

class GetAuthUserList(
    private val securePref: SecurePref
) {

    operator fun invoke(): List<AuthUserModel> {
        return securePref.getUserList().map { it.toAuthUserModel() }
    }
}