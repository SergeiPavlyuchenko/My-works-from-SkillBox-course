package com.skillbox.github.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Network {

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(CustomHeaderInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    val githubApi: GithubApi
        get() = retrofit.create()

}