package com.trevorwiebe.caldav.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.data.model.Event
import com.trevorwiebe.caldav.domain.usecases.GetCalendars
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getCalendars: GetCalendars
): ViewModel() {

    var state by mutableStateOf(MainActivityState())

    init {
        loadCal("test", "test", "https://calendar.mercyh.org/dav.php/calendars/trevorw/default/")
    }

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

            }
        }
    }

    private fun loadCal(username: String, password: String, url: String){
        viewModelScope.launch {
            getCalendars(
                username, password, url
            ).collect{ newResponse ->
                state = state.copy(eventList = newResponse)
            }
        }
    }

}

data class MainActivityState(
    var eventList: List<Event> = emptyList(),
    var username: String = "",
    var password: String = "",
    var url: String = ""
)