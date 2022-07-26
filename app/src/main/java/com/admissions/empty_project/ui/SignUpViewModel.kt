package com.admissions.empty_project.ui

import android.util.Patterns
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
class SignUpViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    data class UiState(
        val validEmail: Boolean = true,
        val validName: Boolean = true,
        val validPhone: Boolean = true,
        val capture: Boolean = true,
    )
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    sealed interface UiEvent{
        object NavigateToDashboard: UiEvent
        object NavigateToGallery : UiEvent
    }
    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    lateinit var email: String
    var phone: String = ""
    var name: String = ""
    var image: String = ""


    private fun checkInputs(): Boolean{
        var check: Boolean
        _state.update { it.copy(capture = !it.capture) }
        _state.update { it.copy(
            validEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches(),
            validPhone = Patterns.PHONE.matcher(phone).matches(),
            validName = name.isNotEmpty()
        ) }
        with(_state.value){ check = validEmail && validName && validPhone }
        return check
    }

    fun onImageClicked() = launch {
        _event.send(UiEvent.NavigateToGallery)
    }

    fun onSaveClicked(){
        if(!checkInputs()) {
            log("check inputs.. email $email, name $name, phone $phone, ${_state.value}")
            return
        }
        launch(Dispatchers.IO) {
            val user = User(email = email, name = name, phone = phone, image = image)
            log("saving,.. email $user")
            userUseCases.insertOrReplace(user)
            _event.send(UiEvent.NavigateToDashboard)
        }
    }

    fun setImageUri(path: String) {
        image = path
        _state.update { it.copy(capture = !it.capture) }
    }
}