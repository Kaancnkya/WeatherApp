package com.example.weatherapp.repo

import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.model.data.WeatherResponse

class RemoteDataSource(
    private val weatherApiService: WeatherApiService
) {

    suspend fun getFromApi() : WeatherResponse{
        return weatherApiService.getWeatherResult()
    }
}