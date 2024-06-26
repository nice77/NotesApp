package ru.work.qa.notesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.work.qa.notesapp.data.local.database.entity.User
import ru.work.qa.notesapp.data.local.database.entity.UserWithNotes

@Dao
interface UserDao {
    @Insert
    fun createUser(user : User) : Long

    @Query("select 1 from users where users.email = :email")
    fun containsEmail(email : String) : Int?

    @Query("select * from users where users.email = :email")
    fun findByEmail(email : String) : User?

    @Query("select * from users where users.id = :id")
    fun findUserWithNotes(id : Long) : UserWithNotes
}