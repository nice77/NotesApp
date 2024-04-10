package ru.work.qa.notesapp.domain.model

data class UserDomainModel(
    val id : Long = 0,
    val email : String,
    val username : String,
    val password : String
)