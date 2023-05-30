package com.example.weatherapp.view.homeFragment

import android.content.Context
import com.example.weatherapp.db.WeatherPropertyDao
import com.example.weatherapp.db.WeatherPropertyDatabase
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.repo.LocalDataSource
import com.example.weatherapp.repo.RemoteDataSource
import com.example.weatherapp.repo.WeatherAppRepository
import com.example.weatherapp.util.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {
    @Provides
    fun provideWeatherService(retrofit : Retrofit) : WeatherApiService{
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    fun provideWeatherDB(weatherPropertyDatabase: WeatherPropertyDatabase) : WeatherPropertyDao{
        return weatherPropertyDatabase.weatherPropertyDao()
    }

    @Provides
    fun provideInternetAvailablity(@ApplicationContext context : Context) : Boolean{
        return NetworkUtil.isInternetAvailable(context)
    }

    @Provides
    fun  provideLocalSource(weatherPropertyDao: WeatherPropertyDao) : LocalDataSource{
        return LocalDataSource(weatherPropertyDao)
    }

    @Provides
    fun provideRemoteSource(weatherApiService: WeatherApiService) : RemoteDataSource{
        return  RemoteDataSource(weatherApiService)
    }

    @Provides
    fun provideWeatherRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        isInternetAvailable : Boolean
    ) : WeatherAppRepository{
        return WeatherAppRepository(remoteDataSource,
            localDataSource,
        isInternetAvailable)
    }
}