package com.trevorwiebe.caldav.domain.model

data class AuthUserModel(
    val username: String,
    val password: String,
    val baseUrl: String
)
