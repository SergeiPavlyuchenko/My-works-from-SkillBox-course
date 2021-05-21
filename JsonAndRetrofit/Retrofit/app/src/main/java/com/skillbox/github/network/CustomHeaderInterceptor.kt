package com.skillbox.github.network

import com.skillbox.github.data.AccessToken
import okhttp3.Interceptor
import okhttp3.Response

class CustomHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "token ${AccessToken.value}")
            .build()

        return chain.proceed(modifiedRequest)
    }


}