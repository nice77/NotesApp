package ru.work.qa.notesapp.data.local.sharedPreferences

import android.content.SharedPreferences
import javax.inject.Inject

class UserIdPreferences @Inject constructor(
    private val preferences: SharedPreferences
) {

    suspend fun getUserId() : Long {
        return preferences.getLong(Keys.userIdKey, -1)
    }

    suspend fun addUserId(id : Long) {
        with(preferences.edit()) {
            putLong(Keys.userIdKey, id)
            commit()
        }
    }

    suspend fun removeUserId() {
        with(preferences.edit()) {
            remove(Keys.userIdKey)
            commit()
        }
    }
}