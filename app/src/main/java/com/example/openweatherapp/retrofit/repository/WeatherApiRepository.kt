package com.example.openweatherapp.retrofit.repository

import com.example.openweatherapp.model.enums.Location
import com.example.openweatherapp.retrofit.response.daily.DailyRoot
import com.example.openweatherapp.retrofit.response.forecast.Root
import retrofit2.Response


interface WeatherApiRepository {
    suspend fun getWeatherWeekly(location: Location): Response<Root>
    suspend fun getWeatherDaily(location: Location): Response<DailyRoot>
}