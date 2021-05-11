package com.example.networking

data class RemoteMovie(
    val imdbId: Long,
    val title: String,
    val year: Int,
    val posterUrl: String
) {
}