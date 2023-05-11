package com.example.weatherapp.view.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.repo.WeatherAppRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory(
    private val weatherAppRepository: WeatherAppRepository
) : ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(weatherAppRepository) as T
        }
        throw IllegalArgumentException("Unknown View model class")
    }

}