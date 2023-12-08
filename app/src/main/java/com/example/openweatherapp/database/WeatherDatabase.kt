package com.example.openweatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.openweatherapp.database.converter.WeatherConverter
import com.example.openweatherapp.database.dao.WeatherDao
import com.example.openweatherapp.model.weekly.ForecastEntity

@Database(
    entities = [
        ForecastEntity::class,
    ], version = 1, exportSchema = false
)
@TypeConverters(
    WeatherConverter::class,
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}