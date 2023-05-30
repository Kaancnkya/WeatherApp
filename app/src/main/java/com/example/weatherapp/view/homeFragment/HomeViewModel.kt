package com.example.weatherapp.view.homeFragment

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.db.WeatherPropertyDatabase
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.model.data.WeatherResponse
import com.example.weatherapp.repo.WeatherAppRepository
import com.example.weatherapp.util.Consts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    weatherAppRepository: WeatherAppRepository
) : ViewModel() {

    val weatherData: LiveData<WeatherResponse> = weatherAppRepository
        .fetchWeatherData()
        .map { weatherResponse ->
            setIcons(weatherResponse)
            weatherResponse
        }.asLiveData()

    private fun setIcons(weatherResponse: WeatherResponse?) {
        val weatherCodes = weatherResponse?.daily?.weathercode
        val icons = ArrayList<Int>()
        weatherCodes?.forEach {
            when (it) {
                0 -> icons.add(Consts.GUNES)
                in 1..3 -> icons.add(Consts.PARCA)
                45, 48 -> icons.add(Consts.SIS)
                51, 53, 55, 56, 57 -> icons.add(Consts.YAGMUR)
                61, 63, 65, 66, 67 -> icons.add(Consts.YAGMUR)
                71, 73, 75, 77 -> icons.add(Consts.KAR)
                80, 81, 82 -> icons.add(Consts.YAGMUR)
                85, 86 -> icons.add(Consts.KAR)
                95 -> icons.add(Consts.YAGMUR)
                96, 99 -> icons.add(Consts.FIRTINA)
            }
        }
        weatherResponse?.icons = icons
    }


}


