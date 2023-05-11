package com.example.weatherapp.view.homeFragment

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.db.WeatherPropertyDatabase
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.model.data.WeatherResponse
import com.example.weatherapp.repo.WeatherAppRepository
import com.example.weatherapp.util.Consts
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(
    private val weatherAppRepository: WeatherAppRepository
) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData

    init {
        fetchData()
    }


    fun fetchData() {
        viewModelScope.launch {
            val response = weatherAppRepository.fetchWeatherData()
            _weatherData.value = response
        }
    }

}


