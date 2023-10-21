package com.trevorwiebe.caldav.data.auth

data class AuthUser(
    val username: String,
    val password: String,
    val baseUrl: String
)
