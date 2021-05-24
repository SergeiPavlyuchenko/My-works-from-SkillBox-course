package com.skillbox.github.ui.repository_list

import android.util.Log
import com.skillbox.github.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class DetailRepoRepository {

    fun isStarred(
        owner: String,
        repo: String,
        isStarred: (RemoteRepo) -> Unit,
        notStarred: (RemoteRepo) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d("githubApi", "start getRepositories")
        Network.githubApi.getRepoDetailInfo(owner, repo).enqueue(
            object : Callback<RemoteRepo> {
                override fun onResponse(call: Call<RemoteRepo>, response: Response<RemoteRepo>) {
                    when(response.code()) {
                        204 -> {
                            Log.d(
                                "githubApi",
                                "response.isSuccessful = ${response.isSuccessful}, response.code = ${response.code()}"
                            )
                            response.body()?.let(isStarred)
                        }
                        404 -> {
                            response.body()?.let(notStarred)
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

}