package com.example.openweatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.openweatherapp.database.WeatherDatabase
import com.example.openweatherapp.database.dao.WeatherDao
import com.example.openweatherapp.database.repository.WeatherDbRepository
import com.example.openweatherapp.database.repository.WeatherDbRepositoryImpl
import com.example.openweatherapp.retrofit.RetrofitBuilder
import com.example.openweatherapp.retrofit.api.WeatherApi
import com.example.openweatherapp.retrofit.repository.WeatherApiRepository
import com.example.openweatherapp.retrofit.repository.WeatherApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWeatherDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

    @Provides
    @Singleton
    fun provideWeatherDbRepository(dao: WeatherDao): WeatherDbRepository {
        return WeatherDbRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi {
        return RetrofitBuilder.weatherApi
    }

    @Provides
    @Singleton
    fun providesWeatherApiRepository(api: WeatherApi): WeatherApiRepository {
        return WeatherApiRepositoryImpl(api)
    }
}