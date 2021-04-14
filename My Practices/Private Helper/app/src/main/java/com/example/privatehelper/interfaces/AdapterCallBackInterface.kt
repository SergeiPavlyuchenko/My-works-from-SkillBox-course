package com.example.privatehelper.interfaces

interface AdapterCallBackInterface {
    fun onItemLongClick(position: Int): Boolean
    fun onLocationButtonClick(): Unit
    fun onRememberButtonClick(): Unit
    fun onEditButtonClick(): Unit
}