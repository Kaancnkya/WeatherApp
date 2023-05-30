package com.example.weatherapp.view.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.model.data.WeatherResponse
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.db.WeatherPropertyDatabase
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.repo.WeatherAppRepository
import com.example.weatherapp.util.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)


        initObserve()

        return binding.root
    }

    fun initObserve() {
        viewModel.weatherData.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(weatherResponse: WeatherResponse?) {

        val adapter = weatherResponse?.let { weatherRes ->
            WeatherDataAdapter(weatherRes) {position ->
                val time = weatherRes.daily?.time?.get(position) ?: ""

                val maxTemp: Float  = weatherRes.daily?.apparentTemperatureMax?.get(position)?.toFloat() ?: 0.0F

                val iconValue = weatherRes.icons[position]

                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(time, maxTemp, iconValue)
                )
            }
        }

        binding.recyclerView.adapter = adapter
    }
}