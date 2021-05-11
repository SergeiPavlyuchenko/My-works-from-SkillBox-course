package com.example.networking.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networking.RemoteMovie

class MoviesViewModel: ViewModel() {

    private val repository = MoviesRepository()
    private val movieListLiveData = MutableLiveData<List<RemoteMovie>>()
    val movieList: LiveData<List<RemoteMovie>>
        get() = movieListLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(text: String, year: String, type: String) {
        isLoadingLiveData.postValue(true)
        repository.searchMovie(text, year, type) { movies ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movies)
        }
    }

}