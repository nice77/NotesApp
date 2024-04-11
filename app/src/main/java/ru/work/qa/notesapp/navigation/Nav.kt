package ru.work.qa.notesapp.navigation

import android.os.Bundle
import androidx.navigation.NavController

interface Nav {

    interface Provider {
        fun getNavController(): NavController?
    }

    fun setNavProvider(navProvider: Provider)
    fun clearNavProvider(navProvider: Provider)

    fun gotoRegisterScreen()
    fun gotoHomeScreen()
    fun gotoAuthScreen()
    fun gotoNoteDetailsScreen(bundle: Bundle? = null)
}