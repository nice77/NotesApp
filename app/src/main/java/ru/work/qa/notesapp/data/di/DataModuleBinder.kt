package ru.work.qa.notesapp.data.di

import dagger.Binds
import dagger.Module
import ru.work.qa.notesapp.data.repository.UserRepositoryImpl
import ru.work.qa.notesapp.domain.repository.UserRepository

@Module
interface DataModuleBinder {

    @Binds
    fun bindUserRepositoryImplToInterface(userRepositoryImpl: UserRepositoryImpl) : UserRepository

}