package com.skillbox.multithreading.threading

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.lifecycle.*
import com.skillbox.multithreading.ResultAction
import com.skillbox.multithreading.ThreadingApplication
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network
import java.lang.Error
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadingViewModel(app: Application) : AndroidViewModel(app) {


    private val repository = (app as ThreadingApplication).getMovieRepository()
    private val mainHandler = Handler(Looper.getMainLooper())

    private val moviesLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = moviesLiveData

    private val isLoadingLiveData =  MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

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
                is ResultAction.Success<List<Movie>> -> {
                    moviesLiveData.postValue(it.data.orEmpty())
                    isLoadingLiveData.postValue(false)
                }
                else -> Error("Incorrect data")
            }
            }
        }
    }

    class ThreadingViewModelFactory(private val application: Application): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ThreadingViewModel(application) as T
        }
    }

}