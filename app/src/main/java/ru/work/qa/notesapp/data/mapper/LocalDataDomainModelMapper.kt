package ru.work.qa.notesapp.data.mapper

import ru.work.qa.notesapp.data.local.database.entity.Note
import ru.work.qa.notesapp.data.local.database.entity.User
import ru.work.qa.notesapp.data.local.database.entity.UserWithNotes
import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.model.UserDomainModel
import ru.work.qa.notesapp.domain.model.UserWithNotesDomainModel
import javax.inject.Inject

class LocalDataDomainModelMapper @Inject constructor(

){

    fun mapUserEntityToDomainModel(user : User) : UserDomainModel {
        return UserDomainModel(
            id = user.id,
            email = user.email,
            username = user.username,
            password = user.password
        )
    }

    fun mapUserEntityToDomainModel(userWithNotes: UserWithNotes) : UserWithNotesDomainModel {
        return UserWithNotesDomainModel(
            user = mapUserEntityToDomainModel(userWithNotes.user),
            notesList = userWithNotes.notesList.map {
                mapNoteEntityToDomainModel(it)
            }
        )
    }

    fun mapNoteEntityToDomainModel(note: Note) : NoteDomainModel {
        return NoteDomainModel(
            id = note.id,
            userId = note.userId,
            header = note.header,
            description = note.description,
            imagePath = note.imagePath
        )
    }

    fun mapDomainModelToUserEntity(userDomainModel: UserDomainModel) : User {
        return User(
            id = userDomainModel.id,
            email = userDomainModel.email,
            username = userDomainModel.username,
            password = userDomainModel.password
        )
    }

}