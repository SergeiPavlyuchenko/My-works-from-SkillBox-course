package com.skillbox.github.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.ui.current_user.CurrentUserRepository
import com.skillbox.github.ui.current_user.RemoteUser

class MainFragmentViewModel: ViewModel() {

    private val repository = MainFragmentRepository()
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