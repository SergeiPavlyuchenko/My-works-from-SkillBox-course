package com.example.networking.network

import com.example.networking.viewModel.MoviesRepository
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {

    val client = OkHttpClient()

    fun getSearchMovieCall(
        text: String,
        year: String,
        type: String
    ): Call {
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .addQueryParameter("s", text)
            .addQueryParameter("type", type)
            .addQueryParameter("y", year)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }

    const val MOVIE_API_KEY = "fc1eb966"


}