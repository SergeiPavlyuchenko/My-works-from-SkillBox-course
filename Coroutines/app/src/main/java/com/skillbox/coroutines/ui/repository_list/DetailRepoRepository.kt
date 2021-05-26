package com.skillbox.coroutines.ui.repository_list

import android.util.Log
import com.skillbox.coroutines.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class DetailRepoRepository {

    fun isStarred(
        owner: String,
        repo: String,
        isStarred: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d("githubApi", "start getRepositories")
        Network.githubApi.getRepoDetailInfo(owner, repo).enqueue(
            object : Callback<RemoteRepo> {
                override fun onResponse(call: Call<RemoteRepo>, response: Response<RemoteRepo>) {
                    when (response.code()) {
                        204 -> {
                            Log.d("githubApi", "response.code = ${response.code()}")
                            isStarred(true)
                        }
                        404 -> {
                            Log.d("githubApi", "response.code = ${response.code()}")
                            isStarred(false)
                        }
                        else -> onError(RuntimeException("Incorrect status code"))

                    }
                }

                override fun onFailure(call: Call<RemoteRepo>, t: Throwable) {
                    Log.d("githubApi", "onFailure")
                    onError(t)
                }
            })

    }


    fun toStarRepo(
        owner: String,
        repo: String,
        toStar: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Network.githubApi.toStarRepo(owner, repo).enqueue(
            object : Callback<RemoteRepo> {
                override fun onResponse(call: Call<RemoteRepo>, response: Response<RemoteRepo>) {
                    if (response.isSuccessful) {
                        toStar(true)
                    } else toStar(false)
                }

                override fun onFailure(call: Call<RemoteRepo>, t: Throwable) {
                    onError(t)
                }
            })
    }

    fun unStarRepo(
        owner: String,
        repo: String,
        toStar: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Network.githubApi.unStarRepo(owner, repo).enqueue(
            object : Callback<RemoteRepo> {
                override fun onResponse(call: Call<RemoteRepo>, response: Response<RemoteRepo>) {
                    if (response.isSuccessful) {
                        toStar(false)
                    } else toStar(true)
                }

                override fun onFailure(call: Call<RemoteRepo>, t: Throwable) {
                    onError(t)
                }
            })
    }

}