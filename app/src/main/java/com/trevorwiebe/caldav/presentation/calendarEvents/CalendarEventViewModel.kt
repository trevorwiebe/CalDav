package com.trevorwiebe.caldav.presentation.calendarEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.usecases.GetCalendarStructure
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarEventViewModel @Inject constructor(
    private val userAuthentication: UserAuthentication,
    private val getCalendarStructure: GetCalendarStructure
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
        }else{
            state = state.copy(calEventList = getCalendarStructure())
        }
    }

}

data class CalendarEventState(
    val calEventList: List<DayUi> = listOf(),
    var authUserModelList: List<AuthUserModel> = emptyList(),
    val isAuthUserListNull: Boolean = false
)