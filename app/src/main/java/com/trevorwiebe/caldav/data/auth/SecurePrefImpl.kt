package com.trevorwiebe.caldav.data.auth

import android.content.SharedPreferences


class SecurePrefImpl(
    private val encryptedSharedPreferences: SharedPreferences
): SecurePref {

    override fun saveUser(authUser: AuthUser) {
        encryptedSharedPreferences.edit()?.apply {
            putString("username", authUser.username)
            putString("password", authUser.password)
            putString("serverUrl", authUser.baseUrl)
            apply()
        }
    }

    override fun getUser(): AuthUser? {
        encryptedSharedPreferences.let {
            val username = it.getString("username", null)
            val password = it.getString("password", null)
            val serverUrl = it.getString("serverUrl", null)
            if (username != null && password != null && serverUrl != null) {
                return AuthUser(username, password, serverUrl)
            }
        }
        return null
    }

}