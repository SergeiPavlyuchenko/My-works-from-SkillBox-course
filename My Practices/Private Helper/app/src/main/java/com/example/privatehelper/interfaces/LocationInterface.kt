package com.example.privatehelper.interfaces

import androidx.activity.result.ActivityResultLauncher

interface LocationInterface {
    fun showCurrentLocationWithPermissionCheck()
    fun askLocationPermission(): ActivityResultLauncher<String>
    fun showLocationRationaleDialog()
    fun showLocationInfo()
    fun acceptPermission(launcher: ActivityResultLauncher<String>)
}
