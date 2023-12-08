package com.example.openweatherapp.retrofit.repository

import com.example.openweatherapp.model.enums.Location
import com.example.openweatherapp.retrofit.api.WeatherApi
import com.example.openweatherapp.retrofit.response.daily.DailyRoot
import com.example.openweatherapp.retrofit.response.forecast.Root
import retrofit2.Response

class WeatherApiRepositoryImpl(
    private val weatherApi: WeatherApi
) : WeatherApiRepository {

    override suspend fun getWeatherWeekly(location: Location): Response<Root> {
        return weatherApi.getForecast(
            location.description,
            "hu",
            "metric",
            "c27b76e885536ddf7b953a117f855677"
        )
    }

    override suspend fun getWeatherDaily(location: Location): Response<DailyRoot> {
        return weatherApi.getDailyWeatherByLocation(
            "${location.description},${location.code}",
            "c27b76e885536ddf7b953a117f855677"
        )
    }
}