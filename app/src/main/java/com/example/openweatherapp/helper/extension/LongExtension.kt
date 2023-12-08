package com.example.openweatherapp.helper.extension

import java.util.Date

fun Long.isOverOneHour(): Boolean {
    try {
        return ((Date().time - Date(this).time) / 60000) > 60
    } catch (e: Exception) {
        throw Throwable("isOverOneHour error", e)
    }
}

fun Long.toDateTime(): String {
    return Date(this).toDateTime()
}