package com.trevorwiebe.caldav.presentation.add_cal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCalendarViewModel @Inject constructor(
    private val userAuthentication: UserAuthentication
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

    private fun saveUser(userName: String, password: String, url: String){
        val authUser = AuthUserModel(
            username = userName,
            password = password,
            baseUrl = url
        )
        userAuthentication.saveAuthUser(authUser)
    }

}

data class AddCalendarState(
    var username: String = "",
    var password: String = "",
    var url: String = "",
)