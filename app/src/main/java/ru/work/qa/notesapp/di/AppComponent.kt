package ru.work.qa.notesapp.di

import android.content.Context
import ru.work.qa.notesapp.data.di.DataModule
import dagger.BindsInstance
import dagger.Component
import ru.work.qa.notesapp.data.di.DataModuleBinder
import ru.work.qa.notesapp.presentation.ui.MainActivity
import ru.work.qa.notesapp.presentation.ui.screens.authenticationScreen.AuthenticationViewModel
import ru.work.qa.notesapp.presentation.ui.screens.homeScreen.HomeViewModel
import ru.work.qa.notesapp.presentation.ui.screens.noteDetailsScreen.NoteDetailsViewModel
import ru.work.qa.notesapp.presentation.ui.screens.registrationScreen.RegisterViewModel
import javax.inject.Singleton


@Component(
    modules = [
        DataModule::class,
        DataModuleBinder::class,
        NavBinderModule::class,
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun provideContext(ctx: Context): Builder

        fun create(): AppComponent

    }

    fun inject(mainActivity: MainActivity)

    fun registerViewModelFactory(): RegisterViewModel.Factory

    fun authenticateViewModelFactory(): AuthenticationViewModel.Factory

    fun homeViewModelFactory(): HomeViewModel.Factory

    fun noteDetailsViewModelFactory(): NoteDetailsViewModel.Factory
}