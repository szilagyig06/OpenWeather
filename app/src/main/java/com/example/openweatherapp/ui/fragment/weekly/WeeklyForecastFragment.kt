package com.example.openweatherapp.ui.fragment.weekly

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.openweatherapp.R
import com.example.openweatherapp.databinding.FragmentWeeklyForecastBinding
import com.example.openweatherapp.model.enums.Location
import com.example.openweatherapp.retrofit.response.forecast.WeatherList
import com.example.openweatherapp.ui.adapter.BaseRecyclerAdapter
import com.example.openweatherapp.ui.adapter.WeeklyWeatherAdapter
import com.example.openweatherapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeeklyForecastFragment : BaseFragment<FragmentWeeklyForecastBinding>(),
    BaseRecyclerAdapter.Listener<WeatherList> {

    private val viewModel by viewModels<WeeklyForecastViewModel>()

    override fun getViewBinding() = FragmentWeeklyForecastBinding.inflate(layoutInflater)

    private lateinit var _adapter: WeeklyWeatherAdapter
    private lateinit var _locationAdapter: ArrayAdapter<Location>
    private var _location = Location.BUDAPEST

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        bindView()

        collect()

        setupEvents()
    }

    private fun setupEvents() {
        with(binding) {
            slRefresh.setOnRefreshListener {
                viewModel.getWeatherWeekly(_location)
                binding.slRefresh.isRefreshing = false
            }

            etSearch.setOnItemClickListener { _, _, i, _ ->
                _locationAdapter?.getItem(i)?.let { _location = it }
            }
        }
    }

    private fun collect() {
        lifecycleScope.launch {
            viewModel.getWeekly()?.collect {
                it?.forecast?.list?.let { it1 -> _adapter.setItems(it1) }
                binding.slRefresh.isRefreshing = false
            }
        }
    }

    private fun bindView() {
        with(binding) {
            rvDays.adapter = _adapter
            binding.etSearch.setAdapter(_locationAdapter)
        }
    }

    private fun setAdapter() {
        _adapter = WeeklyWeatherAdapter(emptyList<WeatherList>().toMutableList(), this)

        _locationAdapter = ArrayAdapter<Location>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            Location.values()
        )
    }

    override fun onItemClick(item: WeatherList) {
        val bundle = Bundle()
        bundle.putLong("day", item.dt)
        NavHostFragment.findNavController(this).navigate(R.id.action_Weekly_to_Daily, bundle)
    }
}