package ru.work.qa.notesapp.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.work.qa.notesapp.data.local.AppDatabase
import ru.work.qa.notesapp.data.local.dao.UserDao

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

}