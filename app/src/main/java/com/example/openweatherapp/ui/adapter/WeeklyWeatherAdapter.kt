package com.example.openweatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.openweatherapp.R
import com.example.openweatherapp.databinding.ItemWeatherBinding
import com.example.openweatherapp.helper.binding.BindingHelper
import com.example.openweatherapp.helper.extension.toDate
import com.example.openweatherapp.helper.extension.toDateTime
import com.example.openweatherapp.helper.extension.toDayShort
import com.example.openweatherapp.retrofit.Constant
import com.example.openweatherapp.retrofit.response.forecast.WeatherList
import com.example.openweatherapp.retrofit.response.forecast.Weather
import com.example.openweatherapp.ui.widget.CircularProgress
import java.util.Date

class WeeklyWeatherAdapter(private val items: MutableList<WeatherList>, listener: Listener<WeatherList>) :
    BaseRecyclerAdapter<WeatherList, ItemWeatherBinding>(items, listener) {

    override fun getViewBinding(parent: ViewGroup): ItemWeatherBinding {
        return ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = items[position]

        with(holder.binding) {
            val icon = weather.weather[0].icon

            tvDay.text = Date(weather.dt * 1000).toDayShort()
            tvDate.text = Date(weather.dt * 1000).toDateTime("yyyy-MM-dd HH:mm")
            tvWeather.text = weather.weather[0].description

            tvTemperature.text = BindingHelper.toCelsius(weather.main.temp)

            Glide.with(root)
                .load(Constant.WEATHER_ICON_URL.format(icon))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(CircularProgress.get(root.context))
                .error(R.drawable.ic_load_error)
                .fitCenter()
                .into(ivWeather)
        }
    }
}