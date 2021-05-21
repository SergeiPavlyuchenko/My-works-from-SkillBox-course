package com.skillbox.github.network

import com.skillbox.github.ui.current_user.RemoteUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/user")
    fun getCurrentUser(
    ): Call<RemoteUser>

}