package ru.work.qa.notesapp.presentation.ui.screens.homeScreen

import android.graphics.BitmapFactory
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.work.qa.notesapp.databinding.ItemNoteBinding
import ru.work.qa.notesapp.domain.model.NoteDomainModel

class NoteViewHolder(
    private val binding : ItemNoteBinding,
    private val onItemPressed: (NoteDomainModel) -> Unit,
    private val onMenuButtonPressed: (NoteDomainModel, View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var note: NoteDomainModel? = null

    init {
        binding.run {
            root.setOnClickListener {
                note?.let {
                    onItemPressed(it)
                }
            }
            imageBtn.setOnClickListener {
                note?.let {
                    onMenuButtonPressed(it, imageBtn)
                }
            }
            note?.let {
                val bitmap = BitmapFactory.decodeFile(it.imagePath)
                image.setImageBitmap(bitmap)
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