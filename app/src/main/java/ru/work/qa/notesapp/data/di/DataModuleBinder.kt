package ru.work.qa.notesapp.data.di

import dagger.Binds
import dagger.Module
import ru.work.qa.notesapp.data.repository.NoteRepositoryImpl
import ru.work.qa.notesapp.data.repository.SharedPreferencesRepositoryImpl
import ru.work.qa.notesapp.data.repository.UserRepositoryImpl
import ru.work.qa.notesapp.domain.repository.NoteRepository
import ru.work.qa.notesapp.domain.repository.SharedPreferencesRepository
import ru.work.qa.notesapp.domain.repository.UserRepository

@Module
interface DataModuleBinder {

    @Binds
    fun bindUserRepositoryImplToInterface(userRepositoryImpl: UserRepositoryImpl) : UserRepository

    @Binds
    fun bindSharedPrefsRepositoryImplToInterface(sharedPreferencesRepositoryImpl: SharedPreferencesRepositoryImpl) : SharedPreferencesRepository

    @Binds
    fun bindNoteRepositoryImplToInterface(noteRepositoryImpl: NoteRepositoryImpl) : NoteRepository
}