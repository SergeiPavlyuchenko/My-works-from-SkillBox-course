package com.example.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Аннотация позволяет преобразовать Json объекты в объекты класса RemoteMovie и наоборот
// generateAdapter = true указывает на то, что нужно создать класс, который будет заниматься
// серриализацией и десерриализацией
@JsonClass(generateAdapter = true)
data class RemoteMovie(
    @Json(name = "Title")
    val title: String,
    @Json(name = "Year")
    val year: Int,
    @Json(name = "Rated")
    val rate: MovieRating = MovieRating.NOT_RATED,
    @Json(name = "Genre")
    val genre: String,
    @Json(name = "Poster")
    val posterUrl: String,
    @Json(name = "Ratings")
    val scores: Map<String, String> = emptyMap()//: List<Score> = emptyList()
) {
}