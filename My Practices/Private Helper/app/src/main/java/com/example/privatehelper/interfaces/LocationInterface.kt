package com.example.privatehelper.interfaces

interface LocationInterface {
    fun showCurrentLocationWithPermissionCheck()
    fun showLocationInfo()
    fun showLocationRationaleDialog()
    fun requestLocationPermission()
    fun ifNeedRationale()
}