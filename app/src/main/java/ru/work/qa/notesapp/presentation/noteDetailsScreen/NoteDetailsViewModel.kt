package ru.work.qa.notesapp.presentation.noteDetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.useCase.DeleteNoteUseCase
import ru.work.qa.notesapp.domain.useCase.SubmitNoteChangesUseCase

class NoteDetailsViewModel @AssistedInject constructor (
    private val submitNoteChangesUseCase: SubmitNoteChangesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create() : NoteDetailsViewModel
    }

    fun submitChanges(noteDomainModel: NoteDomainModel) {
        viewModelScope.launch {
            submitNoteChangesUseCase(noteDomainModel)
        }
    }

    fun deleteNote(noteDomainModel: NoteDomainModel) {
        viewModelScope.launch {
            deleteNoteUseCase(noteDomainModel)
        }
    }
}