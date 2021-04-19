package com.example.privatehelper.interfaces

interface AdapterCallBackInterface {
    fun onItemLongClick(position: Int): Boolean
    fun onLocationButtonClick(hasLocation: Boolean) = Unit
    fun onRemindButtonClick(hasRemind: Boolean, forEdit: Boolean = false) = Unit
    fun onEditButtonClick() = Unit
    fun onAlarmButtonClick() = Unit
}