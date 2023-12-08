package com.example.openweatherapp.ui.fragment.daily

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.openweatherapp.R
import com.example.openweatherapp.databinding.FragmentDailyWeatherBinding
import com.example.openweatherapp.helper.binding.BindingHelper
import com.example.openweatherapp.retrofit.Constant
import com.example.openweatherapp.ui.fragment.BaseFragment
import com.example.openweatherapp.ui.widget.CircularProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyWeatherFragment : BaseFragment<FragmentDailyWeatherBinding>() {
    private val viewModel by viewModels<DailyWeatherViewModel>()

    override fun getViewBinding() = FragmentDailyWeatherBinding.inflate(layoutInflater)

    private var dayId: Long? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleBundle()
        bindView()
        collect()
    }

    private fun bindView() {
        binding.helper = BindingHelper
    }

    private fun handleBundle() {
        dayId = arguments?.getLong("day")
    }

    private fun collect() {
        lifecycleScope.launch {
            viewModel.getWeekly().collect {
                it.let { forecast ->
                    run {
                        val day = forecast.forecast.list.find { it.dt == dayId }
                        binding.day = day
                        day?.weather?.get(0)?.let { it1 -> loadIcon(it1.icon) }
                    }
                }
            }
        }
    }

    private fun loadIcon(icon: String) {
        Glide.with(this)
            .load(Constant.WEATHER_ICON_URL.format(icon))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_load_error)
            .placeholder(CircularProgress.get(requireContext()))
            .into(binding.ivWeatherIcon)
    }
}