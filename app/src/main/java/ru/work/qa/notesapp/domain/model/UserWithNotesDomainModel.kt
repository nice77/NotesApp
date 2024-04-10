package ru.work.qa.notesapp.domain.model


data class UserWithNotesDomainModel(
    val user: UserDomainModel,
    val notesList: List<NoteDomainModel>
)
