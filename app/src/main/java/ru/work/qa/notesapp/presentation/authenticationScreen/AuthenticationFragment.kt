package ru.work.qa.notesapp.presentation.authenticationScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.App
import ru.work.qa.notesapp.R
import ru.work.qa.notesapp.databinding.FragmentAuthenticationBinding
import ru.work.qa.notesapp.di.AssistedViewModelFactory

class AuthenticationFragment : Fragment(R.layout.fragment_authentication) {

    private val binding : FragmentAuthenticationBinding by viewBinding(FragmentAuthenticationBinding::bind)
    private val viewModel : AuthenticationViewModel by viewModels {
        AssistedViewModelFactory(this) {
            (context?.applicationContext as App).component.authenticateViewModelFactory().create()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding.run {
            submitBtn.setOnClickListener {
                println("On button clicked")
                viewModel.checkUserCredentials(
                    email = emailEt.text.toString(),
                    password = passwordEt.text.toString()
                )
            }
            registerTv.setOnClickListener {
                findNavController().navigate(R.id.action_authenticationFragment_to_registerFragment)
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                launch {
                    viewModel.errorFlow.collect { error ->
                        when (error) {
                            ErrorEnum.WRONG_CREDENTIALS -> {
                                showSnackbar(getString(R.string.wrong_credentials))
                            }
                        }
                    }
                }
                launch {
                    viewModel.submitFlow.collect {
                        if (it) {
                            findNavController().navigate(R.id.action_authenticationFragment_to_homeFragment)
                        }
                    }
                }
            }
        }
    }

    private fun showSnackbar(msg : String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_LONG).show()
    }
}