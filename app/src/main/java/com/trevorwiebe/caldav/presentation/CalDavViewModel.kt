package com.trevorwiebe.caldav.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.data.model.Event
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.usecases.GetCalendar
import com.trevorwiebe.caldav.domain.usecases.GetEvents
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getEvents: GetEvents,
    private val getCalendar: GetCalendar,
    private val userAuthentication: UserAuthentication
): ViewModel() {

    var state by mutableStateOf(MainActivityState())

    init {
        loadAuthUser()
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
                loadCalendar(
                    state.username,
                    state.password,
                    state.url
                )
                saveUser(
                    state.username,
                    state.password,
                    state.url
                )
                state = state.copy(
                    username = "",
                    password = "",
                    url = ""
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
            ).collect{ calendar ->
                if(calendar != null) {
                    state = state.copy(
                        calList = state.calList.toMutableList().apply { add(calendar) }
                    )
                }
            }
        }
    }

    private fun loadAuthUser(){
        val authUserList = userAuthentication.getAuthUserList()
        state = state.copy(authUserModelList = authUserList)
        authUserList.forEach { authUser ->
            loadCalendar(authUser.username, authUser.password, authUser.baseUrl)
        }
    }

    private fun saveUser(userName: String, password: String, url: String){
        val authUser = AuthUserModel(
            username = userName,
            password = password,
            baseUrl = url
        )
        userAuthentication.saveAuthUser(authUser)
    }

}

data class MainActivityState(
    val calList: MutableList<Calendar> = mutableListOf(),
    var eventList: List<Event> = emptyList(),
    var username: String = "",
    var password: String = "",
    var url: String = "",
    var authUserModelList: List<AuthUserModel> = emptyList()
)