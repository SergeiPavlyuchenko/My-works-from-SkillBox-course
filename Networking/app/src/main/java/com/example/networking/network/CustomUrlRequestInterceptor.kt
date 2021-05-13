package com.example.networking.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CustomUrlRequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
//        val allQueryParamValues = emptyList<String?>().toMutableList()
//        val allQueryParamNames = originalRequest.url.queryParameterNames
//        allQueryParamNames.forEachIndexed { i, _ ->
//            allQueryParamValues.add(originalRequest.url.queryParameterValue(i))
//        }
//        Log.d("Interceptor", "allQueryParamNames = $allQueryParamNames")
//        Log.d("Interceptor", "allQueryParamValues = $allQueryParamValues")
//        originalRequest.url.newBuilder().removeAllEncodedQueryParameters("123")
//        originalRequest.url.newBuilder().removeAllQueryParameters("321")
//
//        Log.d("Interceptor", "currentQueryParamNames = ${originalRequest.url.queryParameterNames}")
//        Log.d("Interceptor", "currentQueryParamValues = ${originalRequest.url.queryParameterValues("")}")
//        val modifyUrl = originalRequest.url.newBuilder()
//        modifyUrl.addQueryParameter("apikey", MOVIE_API_KEY)
//        allQueryParamNames.forEach { name ->
//            allQueryParamValues.forEach { value ->
//                modifyUrl.addQueryParameter(name, value)
//            }
//        }

        val modifyUrl = originalRequest.url.newBuilder()
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .build()

        val request = Request.Builder()
            .get()
            .url(modifyUrl)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val MOVIE_API_KEY = "fc1eb966"
    }
}