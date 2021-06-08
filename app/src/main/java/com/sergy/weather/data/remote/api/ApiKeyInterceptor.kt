package com.sergy.weather.data.remote.api

import com.sergy.weather.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val withApiKey = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(withApiKey)
            .build()

        return chain.proceed(request)
    }
}