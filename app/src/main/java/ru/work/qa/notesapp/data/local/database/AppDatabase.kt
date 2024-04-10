package ru.work.qa.notesapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.work.qa.notesapp.data.local.database.dao.NoteDao
import ru.work.qa.notesapp.data.local.database.dao.UserDao
import ru.work.qa.notesapp.data.local.database.entity.Note
import ru.work.qa.notesapp.data.local.database.entity.User

@Database(entities = [User::class, Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    abstract fun getNoteDao() : NoteDao
}