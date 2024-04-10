package ru.work.qa.notesapp.domain.useCase

import ru.work.qa.notesapp.domain.model.UserDomainModel
import ru.work.qa.notesapp.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(userDomainModel: UserDomainModel) : Boolean {
        if (!userRepository.containsEmail(userDomainModel.email)) {
            userRepository.createUser(userDomainModel)
            return true
        }
        return false
    }

}