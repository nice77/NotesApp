package ru.work.qa.notesapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val header : String,
    val description : String,
    @ColumnInfo(name = "image_path")
    val imagePath : String
)
