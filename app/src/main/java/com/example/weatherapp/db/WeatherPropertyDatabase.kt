package com.example.weatherapp.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.model.data.CurrentWeather
import com.example.weatherapp.model.data.Daily
import com.example.weatherapp.model.data.WeatherResponse
import com.example.weatherapp.util.DataBaseConverter

@Database(
    entities = [WeatherResponse::class, CurrentWeather::class, Daily::class],
    version = 1,
)
@TypeConverters(DataBaseConverter::class)
abstract class WeatherPropertyDatabase : RoomDatabase() {
    abstract fun weatherPropertyDao(): WeatherPropertyDao


}