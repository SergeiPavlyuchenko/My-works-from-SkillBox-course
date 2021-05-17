package com.example.moshi.adapter

import com.example.moshi.MovieRating
import com.example.moshi.RemoteMovie
import com.example.moshi.Score
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class RemoteMovieCustomJsonAdapter {

    @FromJson
    fun fromJson(customMovie: CustomMovie): RemoteMovie {
        val scores = mutableMapOf<String, String>()
        customMovie.scores.forEach{
            scores[it.score] = it.value
        }
        return RemoteMovie(
            title = customMovie.title,
            year = customMovie.year,
            rate = customMovie.rate,
            genre = customMovie.genre,
            posterUrl = customMovie.posterUrl,
            scores = scores

        )
    }

    fun toJson(movie: RemoteMovie) {

    }

    @JsonClass(generateAdapter = true)
    data class CustomMovie(
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
        val scores: List<Score>
    )
}