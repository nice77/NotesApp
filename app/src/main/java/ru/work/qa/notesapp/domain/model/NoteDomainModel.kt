package ru.work.qa.notesapp.domain.model

import java.io.Serializable


data class NoteDomainModel(
    val id : Long = 0,
    val userId : Long,
    var header : String,
    var description : String,
    var imagePath : String
) : Serializable