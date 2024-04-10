package ru.work.qa.notesapp.domain.useCase

import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){

    suspend operator fun invoke(noteDomainModel: NoteDomainModel) {
        noteRepository.createNote(noteDomainModel)
    }

}