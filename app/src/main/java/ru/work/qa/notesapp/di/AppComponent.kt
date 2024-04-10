package ru.work.qa.notesapp.di

import android.content.Context
import ru.work.qa.notesapp.data.di.DataModule
import dagger.BindsInstance
import dagger.Component
import ru.work.qa.notesapp.data.di.DataModuleBinder
import ru.work.qa.notesapp.presentation.MainActivity
import ru.work.qa.notesapp.presentation.registrationScreen.RegisterViewModel


@Component(modules = [
    DataModule::class,
    DataModuleBinder::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun provideContext(ctx : Context) : Builder

        fun create() : AppComponent

    }

    fun inject(mainActivity : MainActivity)

    fun registerViewModelFactory() : RegisterViewModel.Factory
}