package com.example.networking.viewModel

import android.util.Log
import com.example.networking.RemoteMovie
import com.example.networking.network.Network
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MoviesRepository {
//    http://www.omdbapi.com/?apikey=[yourkey]&s=

    fun searchMovie(
        text: String,
        year: String?,
        type: String?,
        callback: (List<RemoteMovie>) -> Unit,
        errorCallback: (e: Throwable) -> Unit,
    ): Call {

        //Асинхронный метод обращения к серверу
        //Внутри него есть пул потоков на который библиотека выполнит запрос
        //И оповестит с помощью callback об успешном выполнении или ошибке
        return Network.getSearchMovieCall(text, year, type).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Server", "execute request error = ${e.message}", e)
                    errorCallback(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
//                        Log.d("Server", "responseString: $responseString")
                        val movies = parseMovieResponse(responseString)
                        callback(movies)
                    } else
                        errorCallback(IOException())
                }
            })
        }

        //Синхронный метод обращения к серверу
        //При выполнении запроса фоновый поток останавливается и ждёт выполнения запроса
        /*Thread {
            try {
                val response = Network.getSearchMovieCall(text, year, type).execute()
                val responseString = response.body?.string().orEmpty()
                Log.d("Server", "responseString: $responseString")
                val movies = parseMovieResponse(responseString)
                callback(movies)
            } catch (e: IOException) {
                Log.d("Server", "execute request error = ${e.message}", e)
                callback(emptyList())
            }
        }.start()*/
    }

    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie> {
        return try {
            val jsonObject = JSONObject(responseBodyString)
            val movieArray = jsonObject.getJSONArray("Search")
            (0 until movieArray.length()).map { movieArray.getJSONObject(it) }
                .map {
                    val title = it.getString("Title")
                    val year = it.getString("Year")
                    val imdbID = it.getString("imdbID")
                    val type = it.getString("Type")
                    val poster = it.getString("Poster")

                    RemoteMovie(title, year, type, imdbID, poster)
                }
        } catch (e: JSONException) {
            Log.d("Server", "parse response error = ${e.message}", e)
            emptyList()
        }
    }

    /*"Search":[
      {
         "Title":"The Players Club",
         "Year":"1998",
         "imdbID":"tt0119905",
         "Type":"movie",
         "Poster":"https://m.media-amazon.com/images/M/MV5BMTY0NDgwNzg4Ml5BMl5BanBnXkFtZTcwNzg3NTU0NA@@._V1_SX300.jpg"
      },
      }*/
}