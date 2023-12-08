package com.example.openweatherapp.retrofit.api

import com.example.openweatherapp.retrofit.response.daily.DailyRoot
import com.example.openweatherapp.retrofit.response.forecast.Root
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") location: String,
        @Query("lang") language: String,
        @Query("units") units: String,
        @Query("appid") appId: String,
    ): Response<Root>

    @GET("weather")
    suspend fun getDailyWeatherByLocation(
        @Query("q") location: String,
        @Query("appid") appId: String
    ): Response<DailyRoot>
}