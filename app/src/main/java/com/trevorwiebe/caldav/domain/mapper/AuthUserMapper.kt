package com.trevorwiebe.caldav.domain.mapper

import com.trevorwiebe.caldav.data.auth.AuthUser
import com.trevorwiebe.caldav.domain.model.AuthUserModel

fun AuthUser.toAuthUserModel(): AuthUserModel {
    return AuthUserModel(
        username = username,
        password = password,
        baseUrl = baseUrl
    )
}

fun AuthUserModel.toAuthUser(): AuthUser {
    return AuthUser(
        username = username,
        password = password,
        baseUrl = baseUrl
    )
}