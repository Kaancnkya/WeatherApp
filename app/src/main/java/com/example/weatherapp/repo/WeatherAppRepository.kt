package com.example.weatherapp.repo


import com.example.weatherapp.db.WeatherPropertyDao
import com.example.weatherapp.db.WeatherPropertyDatabase
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.model.data.WeatherResponse
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class WeatherAppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val isInternetAvailable: Boolean
) {


    fun fetchWeatherData(): Flow<WeatherResponse> {

        return flow {
            if (isInternetAvailable) {
                val weatherResponse = remoteDataSource.getFromApi()
                localDataSource.fetchFromDataBase()
                emit(weatherResponse)
            } else {
                emit(localDataSource.fetchFromDataBase())
            }
        }
    }


}


