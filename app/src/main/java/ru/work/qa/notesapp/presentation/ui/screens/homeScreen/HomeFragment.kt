package ru.work.qa.notesapp.presentation.ui.screens.homeScreen

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.App
import ru.work.qa.notesapp.R
import ru.work.qa.notesapp.databinding.FragmentHomeBinding
import ru.work.qa.notesapp.di.AssistedViewModelFactory
import ru.work.qa.notesapp.domain.model.NoteDomainModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels {
        AssistedViewModelFactory(this) {
            (context?.applicationContext as App).component.homeViewModelFactory().create()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchNotes()
        binding.run {
            notesRv.adapter = NotesAdapter(
                notesList = mutableListOf(),
                onItemPressed = ::onItemPressed,
                onMenuButtonPressed = ::onMenuButtonPressed
            )
            fab.setOnClickListener {
                viewModel.gotoNoteDetailsScreen(bundle = null)
            }
        }
        observeData()
    }

    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModel.fetchNotes()
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.notesListFlow.collect {
                    val adapterListIsEmpty = (binding.notesRv.adapter as NotesAdapter).notesList.isEmpty()
                    if (it.isEmpty() && adapterListIsEmpty) {
                        binding.notesRv.visibility = View.GONE
                        binding.noNotesTv.visibility = View.VISIBLE
                    } else {
                        binding.noNotesTv.visibility = View.GONE
                        binding.notesRv.visibility = View.VISIBLE
                        (binding.notesRv.adapter as NotesAdapter).updateItems(it.toMutableList())
                    }
                }
            }
        }
    }

    fun onItemPressed(noteDomainModel: NoteDomainModel) {
        viewModel.gotoNoteDetailsScreen(bundle = bundleOf(BUNDLE_KEY to noteDomainModel))
    }

    fun deleteNote(noteDomainModel: NoteDomainModel) {
        viewModel.deleteNote(noteDomainModel)
    }

    fun onMenuButtonPressed(noteDomainModel: NoteDomainModel, view: View) {
        val menu = PopupMenu(requireContext(), view)
        menu.menuInflater.inflate(R.menu.popup_menu, menu.menu)
        menu.setOnMenuItemClickListener { item ->
            return@setOnMenuItemClickListener when (item.itemId) {
                R.id.delete_btn -> {
                    deleteNote(noteDomainModel)
                    fetchNotes()
                    true
                }

                else -> false
            }
        }
        menu.show()
    }

    companion object {
        private const val BUNDLE_KEY = "BUNDLE_KEY"
    }
}