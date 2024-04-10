package ru.work.qa.notesapp.presentation.homeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.work.qa.notesapp.databinding.ItemNoteBinding
import ru.work.qa.notesapp.domain.model.NoteDomainModel

class NotesAdapter(
    val notesList : MutableList<NoteDomainModel>,
    private val onItemPressed: (NoteDomainModel) -> Unit,
    private val onMenuButtonPressed: (NoteDomainModel, View) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemPressed = onItemPressed,
            onMenuButtonPressed = onMenuButtonPressed
        )
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    fun updateItems(newList : MutableList<NoteDomainModel>) {
        val callback = DiffutilCallback(notesList, newList)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        notesList.clear()
        newList.forEach {
            notesList.add(it)
        }
    }
}