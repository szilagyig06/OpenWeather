package com.example.openweatherapp.retrofit.response.forecast

import com.fasterxml.jackson.annotation.JsonProperty

data class Rain(
    @JsonProperty("3h")
    val n3h: Double,
)