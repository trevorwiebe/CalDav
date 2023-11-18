package com.trevorwiebe.caldav.presentation.add_cal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.domain.model.AuthCalendarModel
import com.trevorwiebe.caldav.domain.usecases.discover_cal.LoadAvailableCalendars
import com.trevorwiebe.caldav.domain.usecases.auth.CalendarAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCalendarViewModel @Inject constructor(
    private val calendarAuthentication: CalendarAuthentication,
    private val loadAvailableCalendars: LoadAvailableCalendars
): ViewModel() {

    var state by mutableStateOf(AddCalendarState())

    fun onEvent(event: AddCalendarEvents){
        when(event){
            is AddCalendarEvents.OnUsernameChange -> {
                state = state.copy(username = event.userName)
            }
            is AddCalendarEvents.OnPasswordChange -> {
                state = state.copy(password = event.password)
            }
            is AddCalendarEvents.OnURLChange -> {
                state = state.copy(url = event.url)
            }
            is AddCalendarEvents.OnAddCal -> {
                loadCalendars(state.username, state.password, state.url)
            }
            is AddCalendarEvents.OnCancelDialog -> {
                state = state.copy(authCalendarList = emptyList())
            }
        }
    }

    private fun saveAuthCalendar(userName: String, password: String, url: String, calName: String){
        val authUser = AuthCalendarModel(
            username = userName,
            password = password,
            calendarUrl = url,
            calendarName = calName
        )
        calendarAuthentication.saveAuthCalendar(authUser)
    }

    private fun loadCalendars(username: String, password: String, url: String){
        viewModelScope.launch {
            loadAvailableCalendars(username, password, url).collect{ calList ->
                state = state.copy(
                    authCalendarList = calList.filter { it.calendarName.isNotEmpty() }
                )
            }
        }
    }

}

data class AddCalendarState(
    var username: String = "",
    var password: String = "",
    var url: String = "",
    val authCalendarList: List<AuthCalendarModel> = emptyList()
)