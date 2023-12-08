package com.example.openweatherapp.database.repository

import androidx.annotation.WorkerThread
import com.example.openweatherapp.database.dao.WeatherDao
import com.example.openweatherapp.model.weekly.ForecastEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDbRepositoryImpl @Inject constructor(
    private val dao: WeatherDao
) : WeatherDbRepository {

    @WorkerThread
    override suspend fun insertWeather(weather: ForecastEntity) {
        try {
            dao.insert(weather)
        } catch (error: Throwable) {
            throw InsertError("Unable to save weather", error)
        }
    }

    @WorkerThread
    override suspend fun updateWeather(weather: ForecastEntity) {
        try {
            dao.update(weather)
        } catch (error: Throwable) {
            throw UpdateError("Unable to update weather", error)
        }
    }

    @WorkerThread
    override suspend fun deleteWeather(weather: ForecastEntity) {
        try {
            dao.delete(weather)
        } catch (error: Throwable) {
            throw DeleteError("Unable to delete weather", error)
        }
    }

    override fun getLastWeather(): Flow<ForecastEntity> {
        try {
            return dao.lastWeather
        } catch (error: Throwable) {
            throw FlowError("Unable to get weather", error)
        }
    }
}

class InsertError(message: String, cause: Throwable) : Throwable(message, cause)
class UpdateError(message: String, cause: Throwable) : Throwable(message, cause)
class DeleteError(message: String, cause: Throwable) : Throwable(message, cause)
class FlowError(message: String, cause: Throwable) : Throwable(message, cause)