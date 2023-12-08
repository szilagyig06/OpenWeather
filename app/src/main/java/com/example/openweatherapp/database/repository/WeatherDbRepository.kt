package com.example.openweatherapp.database.repository

import androidx.annotation.WorkerThread
import com.example.openweatherapp.model.weekly.ForecastEntity
import kotlinx.coroutines.flow.Flow

interface WeatherDbRepository {
    @WorkerThread
    suspend fun insertWeather(weather: ForecastEntity)

    @WorkerThread
    suspend fun updateWeather(weather: ForecastEntity)

    @WorkerThread
    suspend fun deleteWeather(weather: ForecastEntity)

    fun getLastWeather(): Flow<ForecastEntity>
}