package ru.work.qa.notesapp.presentation.noteDetailsScreen

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.work.qa.notesapp.App
import ru.work.qa.notesapp.R
import ru.work.qa.notesapp.databinding.FragmentNoteDetailsBinding
import ru.work.qa.notesapp.di.AssistedViewModelFactory
import ru.work.qa.notesapp.domain.model.NoteDomainModel

class NoteDetailsFragment : Fragment(R.layout.fragment_note_details) {

    private val binding : FragmentNoteDetailsBinding by viewBinding(FragmentNoteDetailsBinding::bind)
    private val viewModel : NoteDetailsViewModel by viewModels {
        AssistedViewModelFactory(this) {
            (context?.applicationContext as App).component.noteDetailsViewModelFactory().create()
        }
    }
    private var noteDomainModel : NoteDomainModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            noteDomainModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable(BUNDLE_KEY, NoteDomainModel::class.java)
            } else {
                it.getSerializable(BUNDLE_KEY) as? NoteDomainModel
            }
        }

        binding.run {
            noteDomainModel?.let {
                noteTitleEt.text.append(it.header)
                noteDescriptionEt.text.append(it.description)
            }
            editBtn.setOnClickListener {
                noteTitleEt.isEnabled = !noteTitleEt.isEnabled
                noteDescriptionEt.isEnabled = !noteDescriptionEt.isEnabled
                submitBtn.visibility = if (submitBtn.visibility == View.GONE) View.VISIBLE else View.GONE
                deleteBtn.isEnabled = !deleteBtn.isEnabled
            }
            submitBtn.setOnClickListener {
                noteTitleEt.isEnabled = false
                noteDescriptionEt.isEnabled = false
                it.visibility = View.GONE
                if (noteDomainModel == null) {
                    viewModel.createNote(noteTitleEt.text.toString(), noteDescriptionEt.text.toString())
                } else {
                    viewModel.submitChanges(noteDomainModel!!)
                }
            }
            deleteBtn.setOnClickListener {
                noteDomainModel?.let {
                    viewModel.deleteNote(it)
                    findNavController().navigate(R.id.action_noteDetailsFragment_to_homeFragment)
                }
            }
        }
    }

    companion object {
        private const val BUNDLE_KEY = "BUNDLE_KEY"
    }

}