package com.admissions.empty_project.ui

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
class DashboardViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    data class UiState(
        val list: List<User> = mutableListOf()
    )
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    sealed interface UiEvent{

    }
    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    init {
        launch(Dispatchers.IO) {
            _state.update { it.copy(list = userUseCases.getAll()) }
            log("list init = ${_state.value}")
        }
    }

    fun onAddClicked(){
        log("add clicked")

    }

    fun onDeleteClicked(user: User) {
        log("delet clicked")

    }


}