package ru.work.qa.notesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.work.qa.notesapp.data.local.entity.User

@Dao
interface UserDao {
    @Insert
    fun createUser(user : User)

    @Query("select 1 from users where users.email = :email")
    fun findByEmail(email : String) : Int?
}