package com.skillbox.multithreading.threading

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.multithreading.ResultAction
import com.skillbox.multithreading.ThreadingApplication
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network
import java.lang.Error
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadingViewModel : ViewModel() {

    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    private val workQueue: BlockingQueue<Runnable> =
        LinkedBlockingQueue<Runnable>()

    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        NUMBER_OF_CORES,
        NUMBER_OF_CORES,
        KEEP_ALIVE_TIME,
        KEEP_ALIVE_TIME_UNIT,
        workQueue
    )

    companion object {
        private const val KEEP_ALIVE_TIME = 1L

    }

    private val repository = MovieRepository(threadPoolExecutor)
    private val mainHandler = Handler(Looper.getMainLooper())
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = moviesLiveData

    private val movieIds = listOf(
        "tt0111161",
        "tt0137523",
        "tt0109830",
        "tt0114369",
        "tt0120689",
        "tt0816692",
        "tt0172495"
    )

    fun requestMovies() {
        mainHandler.post {
        repository.fetchMovies(movieIds) {
            when(it) {
                is ResultAction.Success<List<Movie>> -> moviesLiveData.postValue(it.data ?: listOf())
                else -> Error("Incorrect data")
            }
            }
        }
    }

}