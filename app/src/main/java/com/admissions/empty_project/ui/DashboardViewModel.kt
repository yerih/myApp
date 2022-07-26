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
        val list: List<User> = mutableListOf(),
        val image: String = ""
    )
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    sealed interface UiEvent{
        object NavigateToSignUp: UiEvent
    }
    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    init {
        launch(Dispatchers.IO) {
            val list = userUseCases.getAll().reversed()
            _state.update { it.copy(list = list, image = list.first().image) }
        }
    }

    fun onAddClicked(){
        launch (Dispatchers.IO){ _event.send(UiEvent.NavigateToSignUp) }
    }

    fun onDeleteClicked(user: User, pos: Int) {
        launch(Dispatchers.IO) {
            if(userUseCases.getAll().size == 1)return@launch
            if(pos == 0)return@launch
            userUseCases.delete(user)
            _state.update { it.copy(list = userUseCases.getAll().reversed()) }
        }
    }


}