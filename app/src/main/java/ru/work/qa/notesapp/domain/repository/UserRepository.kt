package ru.work.qa.notesapp.domain.repository

import ru.work.qa.notesapp.domain.model.UserDomainModel

interface UserRepository {

    suspend fun createUser(userDomainModel: UserDomainModel)

    suspend fun containsEmail(email : String) : Boolean

    suspend fun findByEmail(email : String) : UserDomainModel?
}