package ru.work.qa.notesapp.domain.useCase

import ru.work.qa.notesapp.domain.repository.SharedPreferencesRepository
import ru.work.qa.notesapp.domain.repository.UserRepository
import javax.inject.Inject

class CheckUserCredentialsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
){

    suspend operator fun invoke(email : String, password : String) : Boolean {
        val foundUser = userRepository.findByEmail(email)
        foundUser?.let {
            if (it.password == password) {
                sharedPreferencesRepository.addUserId(it.id)
                return true
            }
        }
        return false
    }

}