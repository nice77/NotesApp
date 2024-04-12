package ru.work.qa.notesapp.domain.useCase

import ru.work.qa.notesapp.domain.model.UserDomainModel
import ru.work.qa.notesapp.domain.repository.SharedPreferencesRepository
import ru.work.qa.notesapp.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    suspend operator fun invoke(userDomainModel: UserDomainModel) : Boolean {
        if (!userRepository.containsEmail(userDomainModel.email)) {
            val createdUserId = userRepository.createUser(userDomainModel)
            sharedPreferencesRepository.addUserId(createdUserId)
            return true
        }
        return false
    }

}