package com.skillbox.coroutines.ui.repository_list

import android.util.Log
import com.skillbox.coroutines.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class RepoRepository {

    fun getRepositories(
        onComplete: (List<RemoteRepo>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d("githubApi", "start getRepositories")
        Network.githubApi.getRepositories().enqueue(
            object : Callback<List<RemoteRepo>> {
                override fun onResponse(
                    call: Call<List<RemoteRepo>>,
                    response: Response<List<RemoteRepo>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("githubApi", "response.isSuccessful = ${response.isSuccessful}")
                        response.body()?.let(onComplete)
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<List<RemoteRepo>>, t: Throwable) {
                    Log.d("githubApi", "onFailure")
                    onError(t)
                }
            })

    }

}