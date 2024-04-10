package ru.work.qa.notesapp.domain.repository

import ru.work.qa.notesapp.domain.model.UserDomainModel

interface UserRepository {

    suspend fun createUser(userDomainModel: UserDomainModel)

}