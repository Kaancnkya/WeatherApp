package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.db.WeatherPropertyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherDb(@ApplicationContext context : Context) : WeatherPropertyDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherPropertyDatabase::class.java,
            "weather_response_database"
        ).build()
    }
}