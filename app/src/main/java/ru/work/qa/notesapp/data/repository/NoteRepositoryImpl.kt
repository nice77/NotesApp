package ru.work.qa.notesapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.work.qa.notesapp.data.local.database.dao.NoteDao
import ru.work.qa.notesapp.data.mapper.LocalDataDomainModelMapper
import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val localDataDomainModelMapper: LocalDataDomainModelMapper,
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun createNote(noteDomainModel: NoteDomainModel) {
        withContext(Dispatchers.IO) {
            noteDao.createNote(localDataDomainModelMapper.mapDomainModelToNoteEntity(noteDomainModel))
        }
    }

    override suspend fun deleteNote(noteDomainModel: NoteDomainModel) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(localDataDomainModelMapper.mapDomainModelToNoteEntity(noteDomainModel))
        }
    }

    override suspend fun updateNote(noteDomainModel: NoteDomainModel) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(localDataDomainModelMapper.mapDomainModelToNoteEntity(noteDomainModel))
        }
    }
}