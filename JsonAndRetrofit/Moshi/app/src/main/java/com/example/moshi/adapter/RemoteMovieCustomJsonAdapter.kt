package com.example.moshi.adapter

import com.example.moshi.MovieRating
import com.example.moshi.RemoteMovie
import com.example.moshi.Score
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import org.json.JSONObject

class RemoteMovieCustomJsonAdapter {

    @FromJson
    fun fromJson(customMovie: CustomMovie): RemoteMovie {
        val scores = mutableMapOf<String, String>()
        customMovie.scores.forEach {
            scores[it.score] = it.value
        }
        return RemoteMovie(
            id = customMovie.id,
            title = customMovie.title,
            year = customMovie.year,
            rate = customMovie.rate,
            genre = customMovie.genre,
            posterUrl = customMovie.posterUrl,
            scores = scores

        )
    }

    @ToJson
    fun toJson(movie: RemoteMovie): String {
        return movie.id + " " + movie.title + " " + movie.year + " " + movie.rate + " " + movie.genre + " " + movie.posterUrl + " " + movie.scores

    }

    @JsonClass(generateAdapter = true)
    data class CustomMovie(
        @Json(name = "imdbID")
        val id: String,
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