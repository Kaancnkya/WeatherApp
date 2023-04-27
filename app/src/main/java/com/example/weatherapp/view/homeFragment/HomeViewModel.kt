package com.example.weatherapp.view.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.constants.Consts
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.model.data.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {

    private val weatherApiService = WeatherApiService.create()

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData


    fun getDataService() {

        weatherApiService.getWeatherResult().enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    setIcons(weatherResponse)

                    _weatherData.value = weatherResponse
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

            }
        })
    }

    private fun setIcons(weatherResponse: WeatherResponse?) {

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
