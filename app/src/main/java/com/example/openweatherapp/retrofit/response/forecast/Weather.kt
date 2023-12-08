package com.example.openweatherapp.retrofit.response.forecast

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)