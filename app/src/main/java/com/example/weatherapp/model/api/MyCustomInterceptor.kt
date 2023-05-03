package com.example.weatherapp.model.api

import okhttp3.Interceptor
import okhttp3.Response

class MyCustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val myRequest = chain.request()

        myRequest.newBuilder()
            .header("Cache-Control", "public, max-age=" + 90)
            .build()

        return chain.proceed(myRequest)
    }
}