package ru.work.qa.notesapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.work.qa.notesapp.App
import ru.work.qa.notesapp.R
import ru.work.qa.notesapp.databinding.ActivityMainBinding
import ru.work.qa.notesapp.navigation.Nav
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), Nav.Provider {

    @Inject
    lateinit var nav: Nav

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private var currentNavController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        setupNavigation()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
                as NavHostFragment
        currentNavController = navHostFragment.navController
    }

    override fun getNavController(): NavController? = currentNavController

    private fun setupNavigation() {
        nav.setNavProvider(navProvider = this)
    }

    override fun onDestroy() {
        if (::nav.isInitialized) {
            nav.clearNavProvider(this)
        }
        super.onDestroy()
    }
}