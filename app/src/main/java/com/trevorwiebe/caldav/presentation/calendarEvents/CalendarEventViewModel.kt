package com.trevorwiebe.caldav.presentation.calendarEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.model.CalendarModel
import com.trevorwiebe.caldav.domain.model.EventModel
import com.trevorwiebe.caldav.domain.usecases.ConnectEventToDayUI
import com.trevorwiebe.caldav.domain.usecases.GetCalendar
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
    private val getCalendar: GetCalendar,
    private val connectEventToDayUI: ConnectEventToDayUI
): ViewModel() {

    var state by mutableStateOf(CalendarEventState())

    init {
        loadAuthUser()
    }

    private fun loadAuthUser(){

        val dayUiStructure = getCalendarStructure()
        val authUserList = userAuthentication.getAuthUserList()
        state = state.copy(
            authUserModelList = authUserList,
            dayUiList = dayUiStructure
        )
        if(authUserList.isEmpty()){
            state = state.copy(isAuthUserListNull = true)
        }else{
            authUserList.forEach {authUser ->
                loadCalendar(authUser, dayUiStructure)
            }
        }
    }

    private fun loadCalendar(authUserModel: AuthUserModel, dayUiStructure: List<DayUi>){
        viewModelScope.launch {
            getCalendar(
                authUserModel.username, authUserModel.password, authUserModel.baseUrl
            ).collect{ calendar ->
                if(calendar != null) {
                    state = state.copy(
                        calList = state.calList.toMutableList().apply {
                            add(calendar)
                        }
                    )
                }

                loadEvents(authUserModel, dayUiStructure, calendar?.color ?: "#3b3b3b" )
            }
        }
    }

    private fun loadEvents(
        authUserModel: AuthUserModel,
        dayUiStructure: List<DayUi>,
        eventColor: String
    ){
        viewModelScope.launch{
            getEvents(
                authUserModel.username,
                authUserModel.password,
                authUserModel.baseUrl,
                eventColor
            ).collect{eventList ->
                state = state.copy(
                    eventList = state.eventList.toMutableList().apply {
                        addAll(eventList.toMutableList())
                    }
                )
                state = state.copy(
                    dayUiList = connectEventToDayUI(state.eventList, dayUiStructure)
                )
            }
        }
    }

}

data class CalendarEventState(
    val dayUiList: List<DayUi> = listOf(),
    val calList: MutableList<CalendarModel> = mutableListOf(),
    val eventList: MutableList<EventModel> = mutableListOf(),
    var authUserModelList: List<AuthUserModel> = emptyList(),
    val isAuthUserListNull: Boolean = false
)