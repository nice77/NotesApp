package ru.work.qa.notesapp.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.work.qa.notesapp.data.local.database.AppDatabase
import ru.work.qa.notesapp.data.local.database.dao.NoteDao
import ru.work.qa.notesapp.data.local.database.dao.UserDao
import ru.work.qa.notesapp.data.local.sharedPreferences.Keys

@Module
class DataModule {

    @Provides
    fun provideDatabase(ctx : Context) : AppDatabase {
        return Room
            .databaseBuilder(
                context = ctx,
                klass = AppDatabase::class.java,
                name = "CustomTask.db"
            )
            .build()
    }

    @Provides
    fun provideUserDao(db : AppDatabase) : UserDao = db.getUserDao()

    @Provides
    fun provideNoteDao(db : AppDatabase) : NoteDao = db.getNoteDao()

    @Provides
    fun providePreferences(ctx : Context) : SharedPreferences {
        return ctx.getSharedPreferences(Keys.prefsName, Context.MODE_PRIVATE)
    }
}