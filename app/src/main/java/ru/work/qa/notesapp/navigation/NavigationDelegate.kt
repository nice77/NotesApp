package ru.work.qa.notesapp.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDelegate @Inject constructor() {

    private var navProvider: WeakReference<Nav.Provider>? = null

    fun setNavProvider(navProvider: Nav.Provider) {
        this.navProvider = WeakReference(navProvider)
    }

    fun clearNavProvider(navProvider: Nav.Provider) {
        if (this.navProvider?.get() == navProvider) {
            this.navProvider = null
        }
    }

    fun getNavController(): NavController? {
        return navProvider?.get()?.getNavController()
    }

    fun navigate(
        @IdRes actionId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null,
    ) {
        this.navProvider?.get()?.getNavController()?.let { navController ->
            navController.navigate(actionId, args, navOptions, navigatorExtras)
        }
    }
}