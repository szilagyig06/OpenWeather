package com.example.openweatherapp.database.converter

import androidx.room.TypeConverter
import com.example.openweatherapp.retrofit.response.forecast.Root
import com.google.gson.Gson

class WeatherConverter {
    private val gson = Gson()

    @TypeConverter
    fun toModel(string: String?): Root? {
        return string?.let {
            gson.fromJson(it, Root::class.java)
        }
    }

    @TypeConverter
    fun fromModel(model: Root?): String? {
        return model.let { gson.toJson(it) }
    }
}