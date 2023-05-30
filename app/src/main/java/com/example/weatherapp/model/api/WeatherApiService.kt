package com.example.weatherapp.model.api

import android.content.Context
import android.os.Environment
import com.example.weatherapp.model.data.WeatherResponse
import com.example.weatherapp.util.Consts
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getWeatherResult(
        @Query("latitude") latitude: Double = 40.7750,
        @Query("longitude") longitude: Double = 29.9480,
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "auto",
        @Query("daily") daily: String = "weathercode,apparent_temperature_max,apparent_temperature_min",
        @Query("temperature_unit") temperatureUnit: String = "celsius",
        @Query("forecast_days") forecastDays: Int = 14
    ): WeatherResponse

}