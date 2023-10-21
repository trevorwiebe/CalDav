package com.trevorwiebe.caldav.data.auth

interface SecurePref {

    fun saveUser(authUser: AuthUser)

    fun getUser(): AuthUser?
}