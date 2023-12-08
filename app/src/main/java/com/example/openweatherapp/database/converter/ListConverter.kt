package com.example.openweatherapp.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

sealed class ListConverter<T> {
    private val gson = Gson()

    @TypeConverter
    fun toModel(string: String?): List<T>? {
        return string.let {
            val type = object : TypeToken<List<T>>() {}.type
            gson.fromJson(it, type)
        }
    }

    @TypeConverter
    fun fromModel(model: List<T>?): String? {
        return model.let { gson.toJson(it) }
    }
}

