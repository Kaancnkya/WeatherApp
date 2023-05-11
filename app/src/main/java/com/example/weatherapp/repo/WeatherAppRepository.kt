package com.example.weatherapp.repo

import com.example.weatherapp.db.WeatherPropertyDatabase
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.model.data.WeatherResponse
import com.example.weatherapp.util.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import retrofit2.awaitResponse


class WeatherAppRepository(
    private val weatherApiService: WeatherApiService,
    private val weatherDB: WeatherPropertyDatabase,
    private val isInternetAvailable : Boolean
) {
    private val weatherDao = weatherDB.weatherPropertyDao()

    suspend fun fetchWeatherData () : WeatherResponse {

        if (isInternetAvailable) {
            return fetchFromService()

        } else {
            return fetchFromDataBase()
        }
    }

    private suspend fun fetchFromService() : WeatherResponse {

        val response = weatherApiService.getWeatherResult().awaitResponse()
        if (response.isSuccessful){
            val weatherResponse = response.body()
            if (weatherResponse != null){
                insertWeatherToDataBase(weatherResponse)
                setIcons(weatherResponse)
                return weatherResponse
            }
        }
        return fetchFromDataBase()



    }

    private suspend fun fetchFromDataBase() : WeatherResponse {
        return withContext(Dispatchers.IO){
            weatherDao.getAllProperties()
        }


    }

    private suspend fun insertWeatherToDataBase(weatherData : WeatherResponse) {
        withContext(Dispatchers.IO){
            try {
                weatherDB.weatherPropertyDao().insertProperties(weatherData)

            }catch (e: Exception){
                e.printStackTrace()
            }
        }


    }

    suspend fun setIcons(weatherResponse: WeatherResponse?) {
        val weatherCodes = weatherResponse?.daily?.weathercode
        val icons = ArrayList<Int>()
        weatherCodes?.forEach {
            when (it) {
                0 -> icons.add(Consts.GUNES)
                in 1..3 -> icons.add(Consts.PARCA)
                45, 48 -> icons.add(Consts.SIS)
                51, 53, 55, 56, 57 -> icons.add(Consts.YAGMUR)
                61, 63, 65, 66, 67 -> icons.add(Consts.FIRTINA)
                71, 73, 75, 77 -> icons.add(Consts.FIRTINA)
                80, 81, 82 -> icons.add(Consts.KAR)
                85, 86 -> icons.add(Consts.KAR)
                95 -> icons.add(Consts.FIRTINA)
                96, 99 -> icons.add(Consts.FIRTINA)
            }
        }
        weatherResponse?.icons = icons
    }
}