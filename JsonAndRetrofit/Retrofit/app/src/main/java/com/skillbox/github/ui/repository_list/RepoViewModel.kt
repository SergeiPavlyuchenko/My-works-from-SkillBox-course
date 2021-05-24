package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepoViewModel: ViewModel() {

    private val repository = RepoRepository()

    private val remoteRepoListLiveData = MutableLiveData<List<RemoteRepo>>()
    val remoteRepoList: LiveData<List<RemoteRepo>>
        get() = remoteRepoListLiveData

    private val onErrorLiveData = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable>
        get() = onErrorLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun getRepositories() {
        isLoadingLiveData.postValue(true)
        repository.getRepositories ({
            isLoadingLiveData.postValue(false)
            remoteRepoListLiveData.postValue(it.shuffled())
        }) {
            onErrorLiveData.postValue(it)
        }
    }



}