package ru.work.qa.notesapp.domain.repository

interface SharedPreferencesRepository {

    suspend fun addUserId(id : Long)

    suspend fun getUserId() : Long

    suspend fun removeUserId()

}