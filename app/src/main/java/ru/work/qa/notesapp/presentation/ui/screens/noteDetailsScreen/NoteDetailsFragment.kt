package ru.work.qa.notesapp.presentation.ui.screens.noteDetailsScreen

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.work.qa.notesapp.App
import ru.work.qa.notesapp.R
import ru.work.qa.notesapp.databinding.FragmentNoteDetailsBinding
import ru.work.qa.notesapp.di.AssistedViewModelFactory
import ru.work.qa.notesapp.domain.model.NoteDomainModel


class NoteDetailsFragment : Fragment(R.layout.fragment_note_details) {

    private val binding : FragmentNoteDetailsBinding by viewBinding(FragmentNoteDetailsBinding::bind)
    private val viewModel : NoteDetailsViewModel by viewModels {
        AssistedViewModelFactory(this) {
            (context?.applicationContext as App).component.noteDetailsViewModelFactory().create()
        }
    }
    private var noteDomainModel : NoteDomainModel? = null
    private var selectedPath : String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            noteDomainModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable(BUNDLE_KEY, NoteDomainModel::class.java)
            } else {
                it.getSerializable(BUNDLE_KEY) as? NoteDomainModel
            }
        }

        binding.run {
            noteDomainModel?.let {
                noteTitleEt.text?.append(it.header)
                noteDescriptionEt.text?.append(it.description)
                val bitmap = BitmapFactory.decodeFile(it.imagePath)
                noteImage.setImageBitmap(bitmap)
            }

            if (noteDomainModel == null) {
                noteTitleEt.isEnabled = !noteTitleEt.isEnabled
                noteDescriptionEt.isEnabled = !noteDescriptionEt.isEnabled
                extrasBtn.visibility =
                    if (extrasBtn.visibility == View.GONE) View.VISIBLE else View.GONE
                deleteBtn.isEnabled = !deleteBtn.isEnabled
                editBtn.isEnabled = false
            }

            editBtn.setOnClickListener {
                noteTitleEt.isEnabled = !noteTitleEt.isEnabled
                noteDescriptionEt.isEnabled = !noteDescriptionEt.isEnabled
                extrasBtn.visibility =
                    if (extrasBtn.visibility == View.GONE) View.VISIBLE else View.GONE
                deleteBtn.isEnabled = !deleteBtn.isEnabled
            }
            submitBtn.setOnClickListener {
                noteTitleEt.isEnabled = false
                noteDescriptionEt.isEnabled = false
                it.visibility = View.GONE
                if (noteDomainModel == null) {
                    viewModel.createNote(
                        noteTitleEt.text.toString(),
                        noteDescriptionEt.text.toString(),
                        selectedPath
                    )
                } else {
                    noteDomainModel?.let {
                        it.header = noteTitleEt.text.toString()
                        it.description = noteDescriptionEt.text.toString()
                        viewModel.submitChanges(it)
                    }
                }
                viewModel.gotoHomeScreen()
            }
            deleteBtn.setOnClickListener {
                noteDomainModel?.let {
                    viewModel.deleteNote(it)
                }
            }
            pickImageBtn.setOnClickListener {
                startIntent()
            }
        }
    }

    private fun startIntent() {
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        val pictureDirectory = Environment.getExternalStorageDirectory()
        val data = Uri.parse(pictureDirectory.path)
        val type = "image/*"
        intent.setDataAndType(data, type)
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            data?.data?.also { uri ->
                val fullPath = fetchFullPath(uri).getOrNull() ?: ""
                println("Got full path: $fullPath")
                noteDomainModel?.let {
                    it.imagePath = fullPath
                }
                selectedPath = fullPath
                println("Selected path: $selectedPath")
            }
        }
    }

    private fun fetchFullPath(uri : Uri) : Result<String?> {
        var cursor: Cursor? = null
        return runCatching {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = requireContext().contentResolver.query(uri, proj, null, null, null)
            cursor?.let {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                it.moveToFirst()
                return@let it.getString(columnIndex)
            }
        }.onSuccess {
            cursor?.close()
        }
    }

    companion object {
        private const val BUNDLE_KEY = "BUNDLE_KEY"
        private const val IMAGE_REQUEST_CODE = 102
    }
}