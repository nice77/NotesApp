package ru.work.qa.notesapp.navigation

import android.os.Bundle
import ru.work.qa.notesapp.R
import javax.inject.Inject

class NavImpl @Inject constructor(
    private val navigationDelegate: NavigationDelegate,
) : Nav {

    override fun setNavProvider(navProvider: Nav.Provider) {
        navigationDelegate.setNavProvider(navProvider = navProvider)
    }

    override fun clearNavProvider(navProvider: Nav.Provider) {
        navigationDelegate.clearNavProvider(navProvider = navProvider)
    }

    override fun gotoRegisterScreen() {
        navigationDelegate.navigate(actionId = R.id.action_global_register_fragment)
    }

    override fun gotoHomeScreen() {
        navigationDelegate.navigate(actionId = R.id.action_global_home_fragment)
    }

    override fun gotoAuthScreen() {
        navigationDelegate.navigate(actionId = R.id.action_global_auth_fragment)
    }

    override fun gotoNoteDetailsScreen(bundle: Bundle?) {
        navigationDelegate.navigate(actionId = R.id.action_global_note_details_fragment, args = bundle)
    }
}