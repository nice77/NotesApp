package ru.work.qa.notesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import ru.work.qa.notesapp.data.local.database.entity.Note

@Dao
interface NoteDao {
    @Insert
    fun createNote(note: Note)

    @Delete
    fun deleteNote(note : Note)

    @Update
    fun updateNote(note : Note)
}