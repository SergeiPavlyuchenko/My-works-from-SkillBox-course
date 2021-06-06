package com.skillbox.coroutines.ui.repository_list

import android.util.Log
import com.skillbox.coroutines.network.Network
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.yield
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DetailRepoRepository {

    private var needCancel = false

    suspend fun isStarred(
        owner: String,
        repo: String,
    ): Boolean {
        Log.d("githubApi", "start getRepositories")
        return suspendCancellableCoroutine<Boolean> { cancellableContinuation ->
            cancellableContinuation.invokeOnCancellation {
                it?.let { needCancel = true }
            }
            Network.githubApi.getRepoDetailInfo(owner, repo).enqueue(
                object : Callback<RemoteRepo> {
                    override fun onResponse(
                        call: Call<RemoteRepo>,
                        response: Response<RemoteRepo>
                    ) {
                        cancelCall(call, needCancel)
                        when (response.code()) {
                            204 -> {
                                Log.d("githubApi", "response.code = ${response.code()}")
                                cancellableContinuation.resume(true)
                            }
                            404 -> {
                                Log.d("githubApi", "response.code = ${response.code()}")
                                cancellableContinuation.resume(false)
                            }
                            else -> cancellableContinuation.resumeWithException(RuntimeException("Incorrect status code"))

                        }
                    }

                    override fun onFailure(call: Call<RemoteRepo>, t: Throwable) {
                        cancelCall(call, needCancel)
                        Log.d("githubApi", "onFailure")
                        cancellableContinuation.resumeWithException(t)
                    }
                })
        }


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

    private fun cancelCall(call: Call<RemoteRepo>, needCancel: Boolean) {
        if(needCancel) call.cancel()
    }

}