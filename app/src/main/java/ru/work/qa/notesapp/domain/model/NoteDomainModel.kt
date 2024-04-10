package ru.work.qa.notesapp.domain.model

import java.io.Serializable


data class NoteDomainModel(
    val id : Long = 0,
    val userId : Long,
    val header : String,
    val description : String,
    val imagePath : String
) : Serializable