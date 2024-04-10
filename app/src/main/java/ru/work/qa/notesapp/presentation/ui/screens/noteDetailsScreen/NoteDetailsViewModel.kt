package ru.work.qa.notesapp.presentation.ui.screens.noteDetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.useCase.DeleteNoteUseCase
import ru.work.qa.notesapp.domain.useCase.GetCurrentUserIdUseCase
import ru.work.qa.notesapp.domain.useCase.SaveNoteUseCase
import ru.work.qa.notesapp.domain.useCase.SubmitNoteChangesUseCase
import ru.work.qa.notesapp.navigation.Nav

class NoteDetailsViewModel @AssistedInject constructor(
    private val submitNoteChangesUseCase: SubmitNoteChangesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val nav: Nav,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(): NoteDetailsViewModel
    }

    fun submitChanges(noteDomainModel: NoteDomainModel) {
        viewModelScope.launch {
            submitNoteChangesUseCase(noteDomainModel)
        }
    }

    fun createNote(header: String, description: String) {
        viewModelScope.launch {
            val noteDomainModel = NoteDomainModel(
                id = 0,
                userId = getCurrentUserIdUseCase(),
                header = header,
                description = description,
                imagePath = ""
            )
            saveNoteUseCase(noteDomainModel)
        }
    }

    fun deleteNote(noteDomainModel: NoteDomainModel) {
        viewModelScope.launch {
            deleteNoteUseCase(noteDomainModel)
        }
        gotoHomeScreen()
    }

    fun gotoHomeScreen() {
        nav.gotoHomeScreen()
    }
}