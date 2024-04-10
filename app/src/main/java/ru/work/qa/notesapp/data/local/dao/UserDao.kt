package ru.work.qa.notesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import ru.work.qa.notesapp.data.local.entity.User

@Dao
interface UserDao {

    @Insert
    fun createUser(user : User)

}