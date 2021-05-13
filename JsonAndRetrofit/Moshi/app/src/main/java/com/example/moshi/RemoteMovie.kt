package com.example.moshi

data class RemoteMovie(
    val title: String,
    val year: String,
    val type: String,
    val imdbID: String,
    val posterUrl: String
) {
}