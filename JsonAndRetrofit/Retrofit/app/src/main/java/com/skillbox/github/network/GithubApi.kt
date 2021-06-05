package com.skillbox.github.network

import com.skillbox.github.ui.current_user.RemoteUser
import com.skillbox.github.ui.repository_list.DataForPatch
import com.skillbox.github.ui.repository_list.RemoteRepo
import retrofit2.Call
import retrofit2.http.*

interface GithubApi {

    @GET("/user")
    fun getCurrentUser(): Call<RemoteUser>

    @GET("/repositories")
    fun getRepositories(): Call<List<RemoteRepo>>

    @GET("/user/starred/{owner}/{repo}")
    fun getRepoDetailInfo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<RemoteRepo>

    @PUT("/user/starred/{owner}/{repo}")
    fun toStarRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Call<RemoteRepo>

    @DELETE("/user/starred/{owner}/{repo}")
    fun unStarRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Call<RemoteRepo>

    @PATCH("/user")
    fun updateUser(
        @Body dataForPatch: DataForPatch
    ): Call<RemoteUser>

}