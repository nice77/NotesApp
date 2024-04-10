package ru.work.qa.notesapp.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithNotes(
    @Embedded val user : User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val notesList: List<Note>
)
