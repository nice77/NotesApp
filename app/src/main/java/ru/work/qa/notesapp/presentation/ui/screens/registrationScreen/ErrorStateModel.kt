package ru.work.qa.notesapp.presentation.ui.screens.registrationScreen

data class ErrorStateModel(
    val isWrongEmail: Boolean = false,
    val isWrongPassword: Boolean = false,
    val isUserDataIncorrect: Boolean = false,
)
