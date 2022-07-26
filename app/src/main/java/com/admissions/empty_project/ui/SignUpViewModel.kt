package com.admissions.empty_project.ui

import androidx.lifecycle.ViewModel
import com.admissions.usecases.UserUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    data class UiState(
        val any: Boolean = true
    )
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    sealed interface UiEvent{

    }
    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()
}