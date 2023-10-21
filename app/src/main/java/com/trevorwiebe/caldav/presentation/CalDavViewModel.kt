package com.trevorwiebe.caldav.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.data.model.Event
import com.trevorwiebe.caldav.domain.usecases.GetCalendar
import com.trevorwiebe.caldav.domain.usecases.GetEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getEvents: GetEvents,
    private val getCalendar: GetCalendar
): ViewModel() {

    var state by mutableStateOf(MainActivityState())

    fun onEvent(event: CalDavEvents){
        when(event){
            is CalDavEvents.OnUsernameChange -> {
                state = state.copy(username = event.userName)
            }
            is CalDavEvents.OnPasswordChange -> {
                state = state.copy(password = event.password)
            }
            is CalDavEvents.OnURLChange -> {
                state = state.copy(url = event.url)
            }
            is CalDavEvents.OnAddCal -> {
                loadCalendar(
                    state.username,
                    state.password,
                    state.url
                )
            }
        }
    }

    private fun loadEvents(username: String, password: String, url: String){
        viewModelScope.launch {
            getEvents(
                username, password, url
            ).collect{ newResponse ->
                state = state.copy(eventList = newResponse)
            }
        }
    }

    private fun loadCalendar(username: String, password: String, url: String){
        viewModelScope.launch {
            getCalendar(
                username, password, url
            ).collect{ calendarList ->
                state = state.copy(calList = calendarList.sortedBy { it.order })
            }
        }
    }

}

data class MainActivityState(
    val calList: List<Calendar> = emptyList(),
    var eventList: List<Event> = emptyList(),
    var username: String = "",
    var password: String = "",
    var url: String = ""
)