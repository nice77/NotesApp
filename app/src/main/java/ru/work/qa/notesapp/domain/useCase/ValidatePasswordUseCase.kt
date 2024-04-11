package ru.work.qa.notesapp.domain.useCase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(

) {

    operator fun invoke(password : String) : Boolean {
        return password.matches(regex)
    }

    private companion object {
        private val regex = Regex("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*]).{8,}\$")
    }
}