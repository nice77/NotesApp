package ru.work.qa.notesapp.presentation.ui.screens.homeScreen

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.useCase.DeleteNoteUseCase
import ru.work.qa.notesapp.domain.useCase.FetchNotesUseCase
import ru.work.qa.notesapp.domain.useCase.LogoutUseCase
import ru.work.qa.notesapp.navigation.Nav

class HomeViewModel @AssistedInject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val nav: Nav,
) : ViewModel() {

    private val _notesListFlow = MutableStateFlow<List<NoteDomainModel>>(listOf())
    val notesListFlow: StateFlow<List<NoteDomainModel>>
        get() = _notesListFlow

    @AssistedFactory
    interface Factory {
        fun create(): HomeViewModel
    }

    fun fetchNotes() {
        viewModelScope.launch {
            _notesListFlow.emit(fetchNotesUseCase())
        }
    }

    fun deleteNote(noteDomainModel: NoteDomainModel) {
        viewModelScope.launch {
            deleteNoteUseCase(noteDomainModel)
        }
    }

    fun gotoNoteDetailsScreen(bundle: Bundle?) {
        nav.gotoNoteDetailsScreen(bundle = bundle)
    }

    fun gotoAuthScreen() {
        nav.gotoAuthScreen()
    }

    fun removeCurrentUser() {
        viewModelScope.launch {
            logoutUseCase()
            gotoAuthScreen()
        }
    }
}