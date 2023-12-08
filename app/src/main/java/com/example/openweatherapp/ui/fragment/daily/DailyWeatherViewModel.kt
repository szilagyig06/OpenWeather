package com.example.openweatherapp.ui.fragment.daily

import androidx.lifecycle.ViewModel
import com.example.openweatherapp.database.repository.WeatherDbRepository
import com.example.openweatherapp.model.weekly.ForecastEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DailyWeatherViewModel @Inject constructor(
    dbRepository: WeatherDbRepository
) : ViewModel() {

    private var _mWeeklyWeathers = dbRepository.getLastWeather()
    fun getWeekly(): Flow<ForecastEntity> {
        return _mWeeklyWeathers
    }
}