package ru.work.qa.notesapp.presentation.homeScreen

import androidx.recyclerview.widget.RecyclerView
import ru.work.qa.notesapp.databinding.ItemNoteBinding
import ru.work.qa.notesapp.domain.model.NoteDomainModel

class NoteViewHolder(
    private val binding : ItemNoteBinding,
    private val onItemPressed: (NoteDomainModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var note: NoteDomainModel? = null

    init {
        binding.root.setOnClickListener {
            note?.let {
                onItemPressed
            }
        }
    }

    fun bind(note : NoteDomainModel) {
        this.note = note
        binding.run {
            header.text = note.header
        }
    }

}