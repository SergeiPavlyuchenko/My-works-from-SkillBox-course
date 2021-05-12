package com.example.networking.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networking.RemoteMovie
import com.example.networking.UserRequestFromGUI
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

    fun search(userRequest: UserRequestFromGUI) {
        isLoadingLiveData.postValue(true)
        isErrorLiveData.postValue(Pair(null, false))
        currentCall = repository.searchMovie(
            userRequest.title,
            userRequest.year,
            userRequest.type, { movies ->
                isLoadingLiveData.postValue(false)
                movieListLiveData.postValue(movies)
                currentCall = null
            }, {
                isErrorLiveData.postValue(Pair(it, true))
            })
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }

}