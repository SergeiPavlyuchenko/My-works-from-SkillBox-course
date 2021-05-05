package com.skillbox.multithreading.threading


import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network

class MovieRepository {

    private fun initHandler(name: String): HandlerThread {
        return HandlerThread(name)
    }

    fun fetchMovies(
        movieIds: List<String>,
        onMoviesFetch: (movies: List<Movie>) -> Unit
    ) {
        val backgroundThread = initHandler("backgroundThread").apply {
            start()
        }
        val handler = Handler(backgroundThread.looper)
        val movies = mutableListOf<Movie>()
        movieIds.forEach {
            handler.post {
                Network.getMovieById(it)?.let { movie -> movies.add(movie) }
                Log.d("fetchMovies", "add movie in ${Thread.currentThread().name}, movies.size: ${movies.size}")
            }

        }
//        val movies1 = listOf(Movie("Hello", 2333), Movie("Hello1", 2332))
//        handler.post {
            Log.d("fetchMovies", "final movie.size: ${movies.size}")
            onMoviesFetch(movies)
//        }
        handler.looper.quit()
    }

}