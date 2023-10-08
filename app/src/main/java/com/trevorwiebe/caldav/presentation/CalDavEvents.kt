package com.trevorwiebe.caldav.presentation

sealed class CalDavEvents {
    data class OnUsernameChange(val userName: String): CalDavEvents()
    data class OnPasswordChange(val password: String): CalDavEvents()
    data class OnURLChange(val url: String): CalDavEvents()
    object OnAddCal: CalDavEvents()
}
