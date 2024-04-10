package ru.work.qa.notesapp.domain.repository

import ru.work.qa.notesapp.domain.model.UserDomainModel
import ru.work.qa.notesapp.domain.model.UserWithNotesDomainModel

interface UserRepository {

    suspend fun createUser(userDomainModel: UserDomainModel)

    suspend fun containsEmail(email : String) : Boolean

    suspend fun findByEmail(email : String) : UserDomainModel?

    suspend fun findUserWithNotes(id : Long) : UserWithNotesDomainModel
}