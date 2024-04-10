package ru.work.qa.notesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.work.qa.notesapp.data.local.dao.UserDao
import ru.work.qa.notesapp.data.local.entity.Note
import ru.work.qa.notesapp.data.local.entity.User

@Database(entities = [User::class, Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
}