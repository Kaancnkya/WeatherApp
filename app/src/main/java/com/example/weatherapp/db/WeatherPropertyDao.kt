package com.example.weatherapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.model.data.WeatherResponse

@Dao
interface WeatherPropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(properties : WeatherResponse)

    @Query("SELECT * FROM weather_property")
    suspend fun getAllProperties() : WeatherResponse

    @Query("DELETE FROM weather_property")
    suspend fun deleteAll()
}