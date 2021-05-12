package com.example.networking.network

import okhttp3.Interceptor
import okhttp3.Response

class CustomApiKeyInterceptor(
    private val keyName: String,
    private val keyValue: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).newBuilder()
            .addHeader(keyName, keyValue)
            .build()
    }
}