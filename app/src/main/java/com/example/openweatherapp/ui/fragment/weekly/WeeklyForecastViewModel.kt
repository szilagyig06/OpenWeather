package com.example.openweatherapp.ui.fragment.weekly

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweatherapp.database.repository.WeatherDbRepository
import com.example.openweatherapp.helper.extension.isOverOneHour
import com.example.openweatherapp.model.enums.Location
import com.example.openweatherapp.model.weekly.ForecastEntity
import com.example.openweatherapp.retrofit.repository.WeatherApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyForecastViewModel
@Inject constructor(
    private val apiRepository: WeatherApiRepository,
    private val dbRepository: WeatherDbRepository
) : ViewModel() {

    private var _mWeeklyWeathers = dbRepository.getLastWeather()
    fun getWeekly(): Flow<ForecastEntity> {
        return _mWeeklyWeathers
    }

    init {
        getWeatherWeekly()
    }

    fun getWeatherWeekly() { getWeatherWeekly(Location.BUDAPEST) }
    fun getWeatherWeekly(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            _mWeeklyWeathers.collect {
                if (it == null || it.createdAt?.isOverOneHour() == true) {

                    val response = try {
                        apiRepository.getWeatherWeekly(location)
                    } catch (e: Exception) {
                        Log.e("getWeatherWeekly", e.message, e)
                        return@collect
                    }

                    response.body()?.let { body ->
                        dbRepository.insertWeather(ForecastEntity(forecast = body))
                    }
                }
            }
        }
    }
}