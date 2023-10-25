package com.trevorwiebe.caldav.presentation.calendarEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.usecases.ConnectEventToDayUI
import com.trevorwiebe.caldav.domain.usecases.GetCalendarStructure
import com.trevorwiebe.caldav.domain.usecases.GetEvents
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarEventViewModel @Inject constructor(
    private val userAuthentication: UserAuthentication,
    private val getCalendarStructure: GetCalendarStructure,
    private val getEvents: GetEvents,
    private val connectEventToDayUI: ConnectEventToDayUI
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
            val dayUiStructure = getCalendarStructure()
            state = state.copy(calEventList = dayUiStructure)

            val authUser = authUserList[0]
            loadEvents(authUser, dayUiStructure)

        }
    }

    private fun loadEvents(authUserModel: AuthUserModel, calendarUiStructure: List<DayUi>){
        viewModelScope.launch{
            getEvents(
                authUserModel.username,
                authUserModel.password,
                authUserModel.baseUrl
            ).collect{
                val dayUiList = connectEventToDayUI(
                    it,
                    calendarUiStructure
                )
                state = state.copy(calEventList = dayUiList)
            }
        }
    }

}

data class CalendarEventState(
    val calEventList: List<DayUi> = listOf(),
    var authUserModelList: List<AuthUserModel> = emptyList(),
    val isAuthUserListNull: Boolean = false
)