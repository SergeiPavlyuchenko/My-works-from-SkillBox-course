package com.example.networking.viewModel

import android.util.Log
import com.example.networking.RemoteMovie
import com.example.networking.network.Network
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MoviesRepository {
//    http://www.omdbapi.com/?apikey=[yourkey]&s=

    fun searchMovie(
        text: String,
        year: String,
        type: String,
        callback: (List<RemoteMovie>) -> Unit
    ) {

        //Синхронный метод обращения к серверу
        Thread {
            try {
                val response = Network.getSearchMovieCall(text, year, type).execute()
                val responseString =  response.body?.string()

                Log.d("Server", "response string: $responseString" )
                Log.d("Server", "response successful: ${response.isSuccessful}" )

            } catch (e: IOException) {
                Log.d("Server", "execute request error = ${e.message}", e)
            }

            callback(emptyList())
        }.start()

    }
}