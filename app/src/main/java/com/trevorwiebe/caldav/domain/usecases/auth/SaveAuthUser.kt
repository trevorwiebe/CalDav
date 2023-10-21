package com.trevorwiebe.caldav.domain.usecases.auth

import com.trevorwiebe.caldav.data.auth.SecurePref
import com.trevorwiebe.caldav.domain.mapper.toAuthUser
import com.trevorwiebe.caldav.domain.model.AuthUserModel

class SaveAuthUser(
    private val securePref: SecurePref
) {
    operator fun invoke(authUser: AuthUserModel){
        securePref.saveUser(authUser = authUser.toAuthUser())
    }
}