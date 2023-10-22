package com.trevorwiebe.caldav.presentation.calendarEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarEventViewModel @Inject constructor(
    private val userAuthentication: UserAuthentication
): ViewModel() {

    var state by mutableStateOf(CalendarEventState())

    init {
        loadAuthUser()
    }

    private fun loadAuthUser(){
        val authUserList = userAuthentication.getAuthUserList()
        state = state.copy(authUserModelList = authUserList)
        if(authUserList.isEmpty()){
            state = state.copy(isAuthUserListNull = true)
        }
    }

}

data class CalendarEventState(
    val calList: MutableList<Calendar> = mutableListOf(),
    var authUserModelList: List<AuthUserModel> = emptyList(),
    val isAuthUserListNull: Boolean = false
)