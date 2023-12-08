package com.example.openweatherapp.retrofit

import com.example.openweatherapp.retrofit.api.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitBuilder {

    val weatherApi: WeatherApi by lazy {
        buildRetrofit().create(WeatherApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.WEATHER_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
}