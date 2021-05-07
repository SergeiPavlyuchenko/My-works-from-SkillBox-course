package com.skillbox.multithreading.threading


import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.skillbox.multithreading.ResultAction
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network
import java.util.concurrent.Executor

class MovieRepository(
    private val executor: Executor
) {

//    private fun initHandler(name: String): HandlerThread {
//        return HandlerThread(name)
//    }


    fun fetchMovies(
        movieIds: List<String>,
        onMoviesFetch: (movies: ResultAction<List<Movie>>) -> Unit
    ) {
//        val backgroundThread = initHandler("backgroundThread").apply {
//            start()
//        }
//        val handler = Handler(backgroundThread.looper)
        val movies = mutableListOf<Movie>()
        /*handler.post {
        movieIds.forEach {
                Network.getMovieById(it)?.let { movie -> movies.add(movie) }
                Log.d("fetchMovies", "add movie in ${Thread.currentThread().name}, movies.size: ${movies.size}")
        }
            Log.d("fetchMovies", "final movie.size: ${movies.size}")
            onMoviesFetch(movies)
         handler.looper.quit()
        }*/
        executor.execute {
            try {
                val response = makeSynchronousLoginRequest(movieIds, movies)
                onMoviesFetch(response)
            } catch (e: Exception) {
                val errorResult = ResultAction.Error(e)
                onMoviesFetch(errorResult)
            }
        }
    }

    private fun makeSynchronousLoginRequest(
        movieIds: List<String>,
        movies: MutableList<Movie>
    ): ResultAction<List<Movie>> {
        movieIds.forEach {
            Network.getMovieById(it)?.let { movie -> movies.add(movie) }
            Log.d("fetchMovies", "add movie in ${Thread.currentThread().name}, movies.size: ${movies.size}")
        }
        Log.d("fetchMovies", "final movie.size: ${movies.size}")
        return ResultAction.Success(movies)
//        onMoviesFetch(movies)
    }

}