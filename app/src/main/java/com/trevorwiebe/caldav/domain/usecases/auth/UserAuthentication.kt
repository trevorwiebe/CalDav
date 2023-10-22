package com.trevorwiebe.caldav.domain.usecases.auth

data class UserAuthentication(
    val saveAuthUser: SaveAuthUser,
    val getAuthUserList: GetAuthUserList
)
