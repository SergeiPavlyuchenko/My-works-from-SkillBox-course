package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrentUserViewModel: ViewModel() {

    private val repository = CurrentUserRepository()
    private val remoteUserLiveData = MutableLiveData<RemoteUser>()
    val remoteUser: LiveData<RemoteUser>
        get() = remoteUserLiveData
    private val onErrorLiveData = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable>
        get() = onErrorLiveData

    fun getCurrentUser() {
        repository.getCurrentUser({
            remoteUserLiveData.postValue(it)
        }) {
            onErrorLiveData.postValue(it)
        }
    }
}