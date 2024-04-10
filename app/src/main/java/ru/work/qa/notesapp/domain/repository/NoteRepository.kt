package ru.work.qa.notesapp.domain.repository

import ru.work.qa.notesapp.domain.model.NoteDomainModel

interface NoteRepository {

    suspend fun createNote(noteDomainModel: NoteDomainModel)

    suspend fun deleteNote(noteDomainModel: NoteDomainModel)

    suspend fun updateNote(noteDomainModel: NoteDomainModel)
}