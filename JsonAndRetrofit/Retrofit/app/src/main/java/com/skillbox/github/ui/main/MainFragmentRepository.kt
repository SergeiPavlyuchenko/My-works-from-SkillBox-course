package com.skillbox.github.ui.main

import com.skillbox.github.network.Network
import com.skillbox.github.ui.current_user.RemoteUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class MainFragmentRepository {

    fun getCurrentUser(
        onComplete: (RemoteUser) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Network.githubApi.getCurrentUser().enqueue(
            object : Callback<RemoteUser> {
                override fun onResponse(call: Call<RemoteUser>, response: Response<RemoteUser>) {
                    if (response.isSuccessful) {
                        response.body()?.let { onComplete(it) }
                    }
                    onError(RuntimeException("Incorrect status code"))
                }

                override fun onFailure(call: Call<RemoteUser>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }

}