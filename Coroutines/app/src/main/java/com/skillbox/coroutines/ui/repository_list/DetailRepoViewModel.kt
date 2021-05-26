package com.skillbox.coroutines.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailRepoViewModel: ViewModel() {

    private val repository = DetailRepoRepository()

    private val remoteRepoLiveData = MutableLiveData<RemoteRepo>()
    val remoteRepo: LiveData<RemoteRepo>
        get() = remoteRepoLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    private val onErrorLiveData = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable>
        get() = onErrorLiveData

    fun isStarred(repo: RemoteRepo) {
        isLoadingLiveData.postValue(true)
        repository.isStarred (
            repo.owner.owner, repo.name,{
                isLoadingLiveData.postValue(false)
                repo.apply {
                    isStarred = it
                    remoteRepoLiveData.postValue(this)
                }
            }) {
            onErrorLiveData.postValue(it)
        }
    }

    fun toStarRepo(repo: RemoteRepo) {
        isLoadingLiveData.postValue(true)
        repository.toStarRepo (
            repo.owner.owner, repo.name,{
                isLoadingLiveData.postValue(false)
                repo.apply {
                    isStarred = it
                    remoteRepoLiveData.postValue(this)
                }
            }) {
            onErrorLiveData.postValue(it)
        }
    }

    fun unStarRepo(repo: RemoteRepo) {
        isLoadingLiveData.postValue(true)
        repository.unStarRepo (
            repo.owner.owner, repo.name,{
                isLoadingLiveData.postValue(false)
                repo.apply {
                    isStarred = it
                    remoteRepoLiveData.postValue(this)
                }
            }) {
            onErrorLiveData.postValue(it)
        }
    }

}