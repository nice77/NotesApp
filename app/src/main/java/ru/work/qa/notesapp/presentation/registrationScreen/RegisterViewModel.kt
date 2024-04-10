package ru.work.qa.notesapp.presentation.registrationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.domain.model.UserDomainModel
import ru.work.qa.notesapp.domain.useCase.CreateUserUseCase
import ru.work.qa.notesapp.domain.useCase.ValidateEmailUseCase
import ru.work.qa.notesapp.domain.useCase.ValidatePasswordUseCase

class RegisterViewModel @AssistedInject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create() : RegisterViewModel
    }

    fun validateEmail(email : String) : Boolean {
        return validateEmailUseCase(email)
    }

    fun validatePassword(password : String) : Boolean {
        return validatePasswordUseCase(password)
    }

    fun registerUser(userDomainModel: UserDomainModel) {
        viewModelScope.launch {
            createUserUseCase(userDomainModel)
        }
    }
}