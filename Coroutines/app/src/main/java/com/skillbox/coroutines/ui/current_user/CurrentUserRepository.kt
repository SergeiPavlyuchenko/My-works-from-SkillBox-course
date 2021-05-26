package com.skillbox.coroutines.ui.current_user

import android.util.Log
import com.skillbox.coroutines.network.Network
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
        newLocation: String,
        onComplete: (RemoteUser) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Network.githubApi.updateUser(newLocation).enqueue(
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