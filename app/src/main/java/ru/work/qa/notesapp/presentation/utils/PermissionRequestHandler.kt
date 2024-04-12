package ru.work.qa.notesapp.presentation.utils

import androidx.activity.result.contract.ActivityResultContracts
import ru.work.qa.notesapp.presentation.ui.MainActivity
import javax.inject.Inject

class PermissionRequestHandler @Inject constructor(
    private val activity: MainActivity,
    private val showRationaleCallback: (() -> Unit)? = null,
    private val deniedCallback: (() -> Unit)? = null
) {

    private var currentPermission : String? = null

    private val singlePermissionRequest = activity
        .registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                println("Done!")
            } else {
                currentPermission?.let { currentPermission ->
                    if (currentPermission.isNotEmpty() && activity.shouldShowRequestPermissionRationale(currentPermission)) {
                        showRationaleCallback?.invoke()
                    } else {
                        deniedCallback?.invoke()
                    }
                }
            }
    }

    fun requestPermission(permission : String) {
        currentPermission = permission
        singlePermissionRequest.launch(permission)
    }
}