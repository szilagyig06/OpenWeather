package com.example.openweatherapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.openweatherapp.model.weekly.ForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: ForecastEntity)

    @Update
    suspend fun update(weather: ForecastEntity)

    @Delete
    suspend fun delete(weather: ForecastEntity)

    @get:Query("SELECT * FROM ForecastEntity ORDER BY createdAt DESC LIMIT 1")
    val lastWeather: Flow<ForecastEntity>
}