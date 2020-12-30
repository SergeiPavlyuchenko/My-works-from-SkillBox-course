package com.example.a01_generics

sealed class Result<out T: Number, R> {
    abstract val item: Result<Int, String>

    fun returnObject(): Result<Int, String> {
        return item
    }
}





data class Success<T : Number, R> (val newItem: T, override val item: Result<Int, String>) : Result<T, R>()


data class Error<T: Number, R> (val newItem: R, override val item: Result<Int, String>) : Result<T, R>()