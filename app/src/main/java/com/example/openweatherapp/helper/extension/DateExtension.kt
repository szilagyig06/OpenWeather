package com.example.openweatherapp.helper.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}

fun Date.toDateTime(format: String): String {
    return this.format(format)
}

fun Date.toDateTime(): String {
    return this.format("yyyy-MM-dd HH:mm:ss")
}

fun Date.toDate(): String {
    return this.format("yyyy-MM-dd")
}

fun Date.toTime(): String {
    return this.format("HH:mm")
}

fun Date.toDay(): String {
    val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dateFormat
        .format(this)
        .uppercase()
}

fun Date.toDayShort(): String {
    return this.toDay()
        .take(2)
}