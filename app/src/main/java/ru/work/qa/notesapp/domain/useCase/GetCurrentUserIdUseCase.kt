package ru.work.qa.notesapp.domain.useCase

import ru.work.qa.notesapp.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class GetCurrentUserIdUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    suspend operator fun invoke() : Long {
        return sharedPreferencesRepository.getUserId()
    }

}