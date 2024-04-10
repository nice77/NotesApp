package ru.work.qa.notesapp.presentation.registrationScreen

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.work.qa.notesapp.App
import ru.work.qa.notesapp.R
import ru.work.qa.notesapp.databinding.FragmentRegisterBinding
import ru.work.qa.notesapp.di.AssistedViewModelFactory
import ru.work.qa.notesapp.domain.model.UserDomainModel

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding : FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)
    private val viewModel : RegisterViewModel by viewModels {
        AssistedViewModelFactory(this) {
            (context?.applicationContext as App).component.registerViewModelFactory().create()
        }
    }

    private var emailCorrect = false
    private var passwordCorrect = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            emailEt.addTextChangedListener { text ->
                emailCorrect = viewModel.validateEmail(email = text.toString())
                submitBtn.isEnabled = emailCorrect && passwordCorrect
            }
            passwordEt.addTextChangedListener { text ->
                passwordCorrect = viewModel.validatePassword(password = text.toString())
                submitBtn.isEnabled = emailCorrect && passwordCorrect
            }
            submitBtn.setOnClickListener {
                val newUser = UserDomainModel(
                    email = emailEt.text.toString(),
                    username = nameEt.text.toString(),
                    password = passwordEt.text.toString()
                )
                viewModel.registerUser(newUser)
            }
        }
    }

}