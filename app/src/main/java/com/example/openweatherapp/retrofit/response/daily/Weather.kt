package com.example.openweatherapp.retrofit.response.daily

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)
