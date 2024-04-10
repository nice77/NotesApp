package ru.work.qa.notesapp.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.work.qa.notesapp.domain.model.NoteDomainModel
import ru.work.qa.notesapp.domain.useCase.FetchNotesUseCase

class HomeViewModel @AssistedInject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase
): ViewModel() {

    private val _notesListFlow = MutableStateFlow<List<NoteDomainModel>>(listOf())
    val notesListFlow : StateFlow<List<NoteDomainModel>>
        get() = _notesListFlow

    @AssistedFactory
    interface Factory {
        fun create() : HomeViewModel
    }

    fun fetchNotes() {
        viewModelScope.launch {
            _notesListFlow.emit(fetchNotesUseCase())
        }
    }
}