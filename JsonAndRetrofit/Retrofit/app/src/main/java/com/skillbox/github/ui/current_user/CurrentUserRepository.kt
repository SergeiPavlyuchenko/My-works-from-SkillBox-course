package com.skillbox.github.ui.current_user

import android.util.Log
import com.skillbox.github.network.Network
import com.skillbox.github.ui.repository_list.DataForPatch
import com.skillbox.github.ui.repository_list.PatchDataCustomJsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class CurrentUserRepository {

    fun getCurrentUser(
        onComplete: (RemoteUser) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d("githubApi", "start getCurrentUser")
        Network.githubApi.getCurrentUser().enqueue(
            object : Callback<RemoteUser> {
                override fun onResponse(call: Call<RemoteUser>, response: Response<RemoteUser>) {
                    Log.d("githubApi", "response.isSuccessful = ${response.isSuccessful}")
                    if (response.isSuccessful) {
                        Log.d("githubApi", "response.isSuccessful = ${response.isSuccessful}")
                        response.body()?.let(onComplete)
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteUser>, t: Throwable) {
                    onError(t)
                    Log.d("githubApi", "onFailure")
                }
            }
        )
    }

    fun updateUser(
        dataForPatch: DataForPatch,
        onComplete: (RemoteUser) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Network.githubApi.updateUser(dataForPatch).enqueue(
            object : Callback<RemoteUser> {
                override fun onResponse(call: Call<RemoteUser>, response: Response<RemoteUser>) {
                    if (response.isSuccessful) {
                        Log.d("updateUser", "response.isSuccessful = ${response.isSuccessful}")
                        getCurrentUser(onComplete, onError)
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteUser>, t: Throwable) {
                    onError(t)
                    Log.d("githubApi", "onFailure")
                }
            }
        )
    }

}