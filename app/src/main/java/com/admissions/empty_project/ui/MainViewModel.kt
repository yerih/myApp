package com.admissions.empty_project.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
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
) : ViewModel() {
    data class UiState(
        val capture: Boolean = true,
        val put: Boolean = true,
        val validEmail: Boolean = true,
        val email: String = "",
        val image: String = "",
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    sealed interface UiEvent {
        data class NavigateToSignUp(val email: String) : UiEvent
        object NavigateToDashboard : UiEvent
    }

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    var email: String = "hola@c.com"

    init {
        launch(Dispatchers.IO) {
            if (userUseCases.isEmpty()) return@launch
            val user = userUseCases.getAll().reversed().first()
            _state.update { it.copy(email = user.email, image = user.image) }
        }
    }

    private fun captureData() = _state.update { it.copy(capture = !it.capture) }


    fun onLoginClicked() {
        launch(Dispatchers.IO) {
            captureData()
            with(_state.value) {
                when {
                    email.isEmpty() -> _event.send(UiEvent.NavigateToSignUp(email))
                    else -> userUseCases.getUserByEmail(email)?.let { _event.send(UiEvent.NavigateToDashboard) }?: _event.send(UiEvent.NavigateToSignUp(email))
                }
            }
        }
    }
}