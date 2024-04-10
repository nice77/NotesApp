package ru.work.qa.notesapp.domain.useCase

import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.repository.SharedPreferencesRepository
import ru.work.qa.notesapp.domain.repository.UserRepository
import javax.inject.Inject

class FetchNotesUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    private val userRepository: UserRepository
){

    suspend operator fun invoke() : List<NoteDomainModel> {
        val currentUserId = sharedPreferencesRepository.getUserId()
        return userRepository.findUserWithNotes(currentUserId).notesList
    }

}