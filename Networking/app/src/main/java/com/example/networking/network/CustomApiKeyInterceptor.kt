package com.example.networking.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CustomApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifyUrl = originalRequest.url.newBuilder()
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .build()

        val request = originalRequest.newBuilder()
            .get()
            .url(modifyUrl)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val MOVIE_API_KEY = "fc1eb966"
    }
}