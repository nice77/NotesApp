package ru.work.qa.notesapp.di

import dagger.Binds
import dagger.Module
import ru.work.qa.notesapp.navigation.Nav
import ru.work.qa.notesapp.navigation.NavImpl
import javax.inject.Singleton

@Module
interface NavBinderModule {

    @Binds
    @Singleton
    fun bindNavImpl_to_Nav(impl: NavImpl): Nav
}