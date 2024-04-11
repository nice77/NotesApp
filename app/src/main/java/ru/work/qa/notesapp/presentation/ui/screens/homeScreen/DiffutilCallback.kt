package ru.work.qa.notesapp.presentation.ui.screens.homeScreen

import androidx.recyclerview.widget.DiffUtil
import ru.work.qa.notesapp.domain.model.NoteDomainModel

class DiffutilCallback(
    private val oldList: List<NoteDomainModel>,
    private val newList: List<NoteDomainModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldElem = oldList[oldItemPosition]
        val newElem = newList[newItemPosition]
        return oldElem.id == newElem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldElem = oldList[oldItemPosition]
        val newElem = newList[newItemPosition]
        return oldElem.header == newElem.header
                && oldElem.description == newElem.description
                && oldElem.imagePath == newElem.imagePath
    }
}