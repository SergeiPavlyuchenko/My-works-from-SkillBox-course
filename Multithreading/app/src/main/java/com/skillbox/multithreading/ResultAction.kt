package com.skillbox.multithreading

sealed class ResultAction<out R> {
    data class Success<out T>(val data: T) : ResultAction<T>()
    data class Error(val exception: Exception) : ResultAction<Nothing>()
}