package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrentUserViewModel : ViewModel() {

    private val repository = CurrentUserRepository()

    private val remoteUserLiveData = MutableLiveData<RemoteUser>()
    val remoteUser: LiveData<RemoteUser>
        get() = remoteUserLiveData

    private val onErrorLiveData = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable>
        get() = onErrorLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun getCurrentUser() {
        isLoadingLiveData.postValue(true)
        repository.getCurrentUser({
            isLoadingLiveData.postValue(false)
            remoteUserLiveData.postValue(it)
        }) {
            onErrorLiveData.postValue(it)
        }
    }

    fun updateUser(newLocation: String) {
        isLoadingLiveData.postValue(true)
        repository.updateUser(newLocation, {
            it.location = newLocation
            isLoadingLiveData.postValue(false)
            remoteUserLiveData.postValue(it)
        }) {
            onErrorLiveData.postValue(it)
        }
    }
}