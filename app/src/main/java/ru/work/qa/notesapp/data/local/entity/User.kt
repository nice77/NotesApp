package ru.work.qa.notesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val email : String,
    val username : String,
    val password : String
)
