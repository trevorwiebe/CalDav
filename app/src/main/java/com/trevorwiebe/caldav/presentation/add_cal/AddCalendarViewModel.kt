package com.trevorwiebe.caldav.presentation.add_cal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.usecases.LoadAvailableCalendars
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCalendarViewModel @Inject constructor(
    private val userAuthentication: UserAuthentication,
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
//                saveUser(
//                    state.username,
//                    state.password,
//                    state.url
//                )
//                state = state.copy(
//                    username = "",
//                    password = "",
//                    url = ""
//                )
                loadCalendars(state.username, state.password, state.url)
            }
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

    private fun loadCalendars(username: String, password: String, url: String){
        viewModelScope.launch {
            loadAvailableCalendars(username, password, url).collect()
        }
    }

}

data class AddCalendarState(
    var username: String = "",
    var password: String = "",
    var url: String = "",
)