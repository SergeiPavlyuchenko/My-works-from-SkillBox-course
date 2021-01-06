package com.example.a01_generics

sealed class Result<out T, R> {
    data class Success<T, R>(val success: T) : Result<T, R>()
    data class Error<T, R>(val error: R) : Result<T, R>()
}








