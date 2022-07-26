package com.admissions.empty_project.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.admissions.domain.User
import com.admissions.empty_project.common.launch
import com.admissions.empty_project.common.log
import com.admissions.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
): ViewModel() {
    data class UiState(
        val capture: Boolean = true,
        val email: String = "",
        val image: String = "",
    )
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    sealed interface UiEvent{
        object NavigateToSignUp: UiEvent
        object NavigateToDashboard: UiEvent
    }
    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    var email = ""


    init {
        launch(Dispatchers.IO) {
            if(userUseCases.isEmpty())return@launch
            val users = userUseCases.getAll()

        }
    }

    fun onLoginClicked(){
        _state.update { it.copy(capture = !it.capture) }
        launch(Dispatchers.IO) {
            userUseCases.getUserByEmail(email)?.let { _event.send(UiEvent.NavigateToDashboard) } ?:_event.send(UiEvent.NavigateToSignUp)
        }
    }
}