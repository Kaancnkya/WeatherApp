package com.example.weatherapp.repo

import com.example.weatherapp.db.WeatherPropertyDao
import com.example.weatherapp.model.data.WeatherResponse

class LocalDataSource(
    private val weatherDao : WeatherPropertyDao
) {
    private suspend fun insertWeatherToDataBase(weatherData: WeatherResponse) {

        try {
            weatherDao.deleteAll()
            weatherDao.insertProperties(weatherData)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    suspend fun fetchFromDataBase(): WeatherResponse {
        return weatherDao.getAllProperties()

    }
}