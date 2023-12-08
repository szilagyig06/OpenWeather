package com.example.openweatherapp.retrofit.response.forecast

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherList(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val sys: Sys,
    val dtTxt: String,
    val rain: Rain?,
)
