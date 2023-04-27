package com.example.weatherapp.model.api

import android.os.Environment
import com.example.weatherapp.constants.Consts
import com.example.weatherapp.model.data.WeatherResponse
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherApiService {
    @GET("v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&daily=weathercode,apparent_temperature_max,apparent_temperature_min&timezone=auto&temperature_unit=celsius&forecast_days=14")
    fun getWeatherResult() : Call<WeatherResponse>

    companion object{

        fun create() : WeatherApiService {

            val httpLogingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(Environment.getExternalStorageDirectory(),cacheSize)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLogingInterceptor)
                .cache(myCache)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}