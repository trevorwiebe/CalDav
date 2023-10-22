package com.trevorwiebe.caldav.presentation.calendarList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.data.model.Calendar
import com.trevorwiebe.caldav.domain.model.AuthUserModel
import com.trevorwiebe.caldav.domain.usecases.GetCalendar
import com.trevorwiebe.caldav.domain.usecases.auth.UserAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarListViewModel @Inject constructor(
    private val getCalendar: GetCalendar,
    private val userAuthentication: UserAuthentication
): ViewModel() {

    var state by mutableStateOf(CalendarListState())

    init {
        loadAuthUser()
    }

    private fun loadAuthUser(){
        val authUserList = userAuthentication.getAuthUserList()
        state = state.copy(authUserModelList = authUserList)
        authUserList.forEach { authUser ->
            loadCalendar(authUser.username, authUser.password, authUser.baseUrl)
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
}

data class CalendarListState(
    val calList: MutableList<Calendar> = mutableListOf(),
    var authUserModelList: List<AuthUserModel> = emptyList(),
)