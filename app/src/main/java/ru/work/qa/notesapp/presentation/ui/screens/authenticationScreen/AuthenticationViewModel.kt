package ru.work.qa.notesapp.presentation.ui.screens.authenticationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.domain.useCase.CheckUserCredentialsUseCase
import ru.work.qa.notesapp.domain.useCase.GetCurrentUserIdUseCase
import ru.work.qa.notesapp.navigation.Nav

class AuthenticationViewModel @AssistedInject constructor(
    private val checkUserCredentialsUseCase: CheckUserCredentialsUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val nav: Nav,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<ErrorEnum>()
    val errorFlow: SharedFlow<ErrorEnum>
        get() = _errorFlow

    private val _submitFlow = MutableStateFlow(false)
    val submitFlow: StateFlow<Boolean>
        get() = _submitFlow

    @AssistedFactory
    interface Factory {
        fun create(): AuthenticationViewModel
    }

    fun checkUserCredentials(email: String, password: String) {
        viewModelScope.launch {
            val resultBoolean = checkUserCredentialsUseCase(email, password)
            if (!resultBoolean) {
                _errorFlow.emit(ErrorEnum.WRONG_CREDENTIALS)
            } else {
                _submitFlow.emit(resultBoolean)
            }
        }
    }

    fun isSessionActive() {
        viewModelScope.launch {
            val isActive = getCurrentUserIdUseCase() != -1L
            _submitFlow.emit(isActive)
        }
    }

    fun gotoRegisterScreen() {
        nav.gotoRegisterScreen()
    }

    fun gotoHomeScreen() {
        nav.gotoHomeScreen()
    }
}