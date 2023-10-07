package com.trevorwiebe.caldav.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevorwiebe.caldav.domain.usecases.GetCalendars
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "MainActivityViewModel"

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getCalendars: GetCalendars
): ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityState())
    val uiState: StateFlow<MainActivityState> = _uiState.asStateFlow()

    init {
        fetchEvents()
    }

    private fun fetchEvents(){
        viewModelScope.launch {
            getCalendars().collect{ newResponse ->
                _uiState.update { it.copy(responseString = newResponse) }
            }
        }
    }

}

data class MainActivityState(
    val responseString: String = ""
)