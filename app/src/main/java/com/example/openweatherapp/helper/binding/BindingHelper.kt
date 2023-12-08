package com.example.openweatherapp.helper.binding

import com.example.openweatherapp.helper.extension.format
import com.example.openweatherapp.helper.extension.toDay
import com.example.openweatherapp.helper.extension.toTime
import java.util.Date
import kotlin.math.round

object BindingHelper {
    fun timestampToDate(timestamp: Long): String {
        return Date(timestamp * 1000).format("yyyy-MM-dd HH:mm")
    }

    fun timestampToTime(timestamp: Long): String {
        return Date(timestamp * 1000).toTime()
    }

    fun timestampToDay(timestamp: Long): String {
        return Date(timestamp * 1000).toDay()
    }

    fun toCelsius(temp: Double): String {
        return "${round(temp)}°C"
    }

//    fun toMinMax(temp: Temp?): String {
//        return "${temp?.max}°C/${temp?.min}°C"
//    }
}