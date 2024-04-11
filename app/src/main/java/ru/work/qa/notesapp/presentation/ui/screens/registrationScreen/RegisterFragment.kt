package ru.work.qa.notesapp.presentation.ui.screens.registrationScreen

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.work.qa.notesapp.App
import ru.work.qa.notesapp.R
import ru.work.qa.notesapp.databinding.FragmentRegisterBinding
import ru.work.qa.notesapp.di.AssistedViewModelFactory
import ru.work.qa.notesapp.presentation.utils.observe

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)
    private val viewModel: RegisterViewModel by viewModels {
        AssistedViewModelFactory(this) {
            (context?.applicationContext as App).component.registerViewModelFactory().create()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.run {
            nameEt.doOnTextChanged { text, _, _, _ ->
                nameHolderLayout.error = null
                viewModel.onUsernameChanged(username = text.toString())
            }

            emailEt.doOnTextChanged { text, _, _, _ ->
                emailHolderLayout.error = null
                viewModel.onEmailChanged(email = text.toString())
            }

            passwordEt.doOnTextChanged { text, _, _, _ ->
                viewModel.onPasswordChanged(password = text.toString())
            }

            submitBtn.setOnClickListener(viewModel::registerUser)

            signInTv.setOnClickListener(viewModel::gotoAuthScreen)
        }
    }

    private fun observeData() {
        with(binding) {
            viewModel.errorFlow.observe(fragment = this@RegisterFragment) { error ->
                when (error) {
                    ErrorEnum.EMAIL_FORMAT_ERROR -> {
                        emailHolderLayout.error = getString(R.string.email_wrong_format)
                    }

                    ErrorEnum.PASSWORD_FORMAT_ERROR -> {
                        passwordHolderLayout.error = getString(R.string.password_wrong_format)
                    }

                    ErrorEnum.EMAIL_EXISTS -> {
                        showSnackbar(getString(R.string.email_exists))
                    }

                    ErrorEnum.USERNAME_IS_EMPTY -> {
                        nameHolderLayout.error = getString(R.string.empty_username)
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_LONG).show()
    }
}