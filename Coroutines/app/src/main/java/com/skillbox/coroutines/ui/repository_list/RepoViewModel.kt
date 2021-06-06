package com.skillbox.coroutines.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class RepoViewModel: ViewModel() {

    private val repository = RepoRepository()
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

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
        viewModelScope.launch {
            isLoadingLiveData.postValue(true)
            try {
                val repositories = repository.getRepositories()
                remoteRepoListLiveData.postValue(repositories)
            } catch (t: Throwable) {
                onErrorLiveData.postValue(t)
            } finally {
                isLoadingLiveData.postValue(false)
            }

        }
    }

/*    override fun onCleared() {
        super.onCleared()
        // Обязательно если используем не SupervisorJob() (отменяем все дочерние корутины за счёт
       // отмены родительской Job() в scope)
        viewModelScope.cancel()
    }*/

}