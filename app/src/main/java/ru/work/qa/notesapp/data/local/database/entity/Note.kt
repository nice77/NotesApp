package ru.work.qa.notesapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        )
    ]
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    @ColumnInfo(name = "user_id")
    val userId : Long,
    val header : String,
    val description : String,
    @ColumnInfo(name = "image_path")
    val imagePath : String
)
