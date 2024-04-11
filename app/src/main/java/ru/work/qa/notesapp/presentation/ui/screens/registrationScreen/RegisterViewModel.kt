package ru.work.qa.notesapp.presentation.ui.screens.registrationScreen

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.domain.model.UserDomainModel
import ru.work.qa.notesapp.domain.useCase.CreateUserUseCase
import ru.work.qa.notesapp.domain.useCase.ValidateEmailUseCase
import ru.work.qa.notesapp.domain.useCase.ValidatePasswordUseCase
import ru.work.qa.notesapp.navigation.Nav

class RegisterViewModel @AssistedInject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val nav: Nav,
) : ViewModel() {

    private var userEmail: String = ""
    private var userPassword: String = ""
    private var username: String = ""

    private val _errorFlow = MutableStateFlow<ErrorEnum?>(null)
    val errorFlow: StateFlow<ErrorEnum?>
        get() = _errorFlow.asStateFlow()

    fun onEmailChanged(email: String) {
        this.userEmail = if (validateEmailUseCase.invoke(email = email)) {
            email
        } else {
            ""
        }
    }

    fun onPasswordChanged(password: String) {
        this.userPassword = if (validatePasswordUseCase.invoke(password = password)) {
            password
        } else {
            ""
        }
    }

    fun onUsernameChanged(username: String) {
        this.username = username
    }

    fun registerUser(view: View) {
        if (userEmail.isEmpty()) {
            _errorFlow.value = ErrorEnum.EMAIL_FORMAT_ERROR
        }

        if (userPassword.isEmpty()) {
            _errorFlow.value = ErrorEnum.PASSWORD_FORMAT_ERROR
        }

        if (username.isEmpty()) {
            _errorFlow.value = ErrorEnum.USERNAME_IS_EMPTY
        }

        val isUserDataValid = userEmail.isNotEmpty() && userPassword.isNotEmpty() && username.isNotEmpty()

        if (isUserDataValid) {
            val userDomainModel = UserDomainModel(
                email = userEmail,
                username = username,
                password = userPassword,
            )
            viewModelScope.launch {
                val result = createUserUseCase(userDomainModel)
                if (result) {
                    nav.gotoHomeScreen()
                } else {
                    _errorFlow.emit(ErrorEnum.EMAIL_EXISTS)
                }
            }
        }
    }

    fun gotoAuthScreen(view: View) {
        nav.gotoAuthScreen()
    }

    @AssistedFactory
    interface Factory {
        fun create(): RegisterViewModel
    }
}