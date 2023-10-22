package com.trevorwiebe.caldav.presentation.add_cal

sealed class AddCalendarEvents {
    data class OnUsernameChange(val userName: String) : AddCalendarEvents()
    data class OnPasswordChange(val password: String) : AddCalendarEvents()
    data class OnURLChange(val url: String) : AddCalendarEvents()
    object OnAddCal: AddCalendarEvents()
}
