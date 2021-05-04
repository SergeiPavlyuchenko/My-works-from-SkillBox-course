package com.skillbox.multithreading.threading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network

class ThreadingViewModel : ViewModel() {

    private val repository = MovieRepository()
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private val movies: LiveData<List<Movie>>
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
        val movies = movieIds.mapNotNull {
            Network.getMovieById(it)
        }
        moviesLiveData.postValue(movies)
    }

}