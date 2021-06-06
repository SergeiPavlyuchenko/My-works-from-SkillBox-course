package com.skillbox.coroutines.ui.repository_list

import android.util.Log
import com.skillbox.coroutines.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class RepoRepository {

    suspend fun getRepositories(): List<RemoteRepo> {
        Log.d("githubApi", "start getRepositories")
        return withContext(Dispatchers.Default) {
            var repositories: List<RemoteRepo> = emptyList()
            Network.githubApi.getRepositories().execute().body()?.let { repositories = it }
            repositories.shuffled()
        }
    }

}