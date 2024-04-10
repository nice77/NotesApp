package ru.work.qa.notesapp

import android.app.Application
import ru.work.qa.notesapp.di.AppComponent
import ru.work.qa.notesapp.di.DaggerAppComponent

class App : Application() {

    private lateinit var _component: AppComponent
    val component : AppComponent
        get() = _component

    override fun onCreate() {
        super.onCreate()
        _component = DaggerAppComponent
            .builder()
            .provideContext(applicationContext)
            .create()
    }

}