package com.example.a01_generics

sealed class Result<out T: Any, R> {
    abstract val item: Result<Int, String>

    fun returnObject(): Result<Int, String> {
        return item
    }
}





data class Success<T : Any, R> (val newItem: T, override val item: Result<Int, String>) : Result<T, R>()


data class Error<T: Any, R> (val newItem: R, override val item: Result<Int, String>) : Result<T, R>()