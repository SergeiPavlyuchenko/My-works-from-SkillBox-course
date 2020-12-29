package com.example.a01_generics

sealed class Result<out T, R> {





}

fun returnObject(): Result<Int, String> {
    return object : Result<Int, String>
}


data class Success<T, R> (val item: T) : Result<T, R>()


data class Error<T, R> (val item: R) : Result<T, R>()