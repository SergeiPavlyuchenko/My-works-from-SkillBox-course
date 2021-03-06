package com.example.moshi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moshi.RemoteMovie
import com.example.moshi.UserRequestFromGUI
import okhttp3.Call

class MoviesViewModel : ViewModel() {

    private val repository = MoviesRepository()

    // Если пользователю не важен результат запроса, например, он закрыл приложение
    // или перевернул экран во время него, то это необходимо обработать:
    // отменить его в onCleared() и занулить его после его  успешного выполнения.
    private var currentCall: Call? = null

    private val movieListLiveData = MutableLiveData<List<RemoteMovie>>()
    val movieList: LiveData<List<RemoteMovie>>
        get() = movieListLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    private val isErrorLiveData = MutableLiveData<Pair<Throwable?, Boolean>>()
    val isError: LiveData<Pair<Throwable?, Boolean>>
        get() = isErrorLiveData

    private val notFoundLiveData = MutableLiveData<Boolean>()
    val notFound: LiveData<Boolean>
        get() = notFoundLiveData

    fun search(userRequest: UserRequestFromGUI) {
        isLoadingLiveData.postValue(true)
        isErrorLiveData.postValue(Pair(null, false))
        notFoundLiveData.postValue(false)
        currentCall = repository.searchMovie(
            userRequest.title,
            userRequest.year,
            userRequest.type, { movies ->
                isLoadingLiveData.postValue(false)
                movieListLiveData.postValue(movies)
                currentCall = null
            }, {
                isErrorLiveData.postValue(Pair(it, true))
            }, {
                notFoundLiveData.postValue(true)
                isLoadingLiveData.postValue(false)
            })
    }

    fun modifyItemScore(movies: List<RemoteMovie>, score: String, value: String, position: Int) {
        isLoadingLiveData.postValue(true)
        val updatedList = repository.modifyItemScore(
            movies,
            score,
            value,
            position
        )
        movieListLiveData.postValue(updatedList)
        isLoadingLiveData.postValue(false)
    }


    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }

}