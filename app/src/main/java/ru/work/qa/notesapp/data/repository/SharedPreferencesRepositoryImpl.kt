package ru.work.qa.notesapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.work.qa.notesapp.data.local.sharedPreferences.UserIdPreferences
import ru.work.qa.notesapp.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    private val userIdPreferences: UserIdPreferences
) : SharedPreferencesRepository {
    override suspend fun addUserId(id: Long) {
        withContext(Dispatchers.IO) {
            userIdPreferences.addUserId(id)
        }
    }

    override suspend fun getUserId(): Long {
        return withContext(Dispatchers.IO) {
            userIdPreferences.getUserId()
        }
    }

    override suspend fun removeUserId() {
        withContext(Dispatchers.IO) {
            userIdPreferences.removeUserId()
        }
    }
}