package com.example.openweatherapp.model.weekly

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.openweatherapp.retrofit.response.forecast.Root

@Entity
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),

    val forecast: Root
)