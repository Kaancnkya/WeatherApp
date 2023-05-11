package com.example.weatherapp.view.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.data.WeatherResponse
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.db.WeatherPropertyDatabase
import com.example.weatherapp.model.api.WeatherApiService
import com.example.weatherapp.repo.WeatherAppRepository
import com.example.weatherapp.util.NetworkUtil

import java.text.DateFormat
import java.util.Calendar

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val weatherApiService = WeatherApiService.create(requireContext())
        val weatherDB = WeatherPropertyDatabase.getInstance(requireContext())
        val hasInternet = NetworkUtil.isInternetAvailable(requireContext())
        val weatherAppRepository = WeatherAppRepository(weatherApiService, weatherDB, hasInternet)

        viewModel = ViewModelProvider(
            requireActivity(),
            HomeViewModelFactory(weatherAppRepository),
        )[HomeViewModel::class.java]

        if (viewModel.weatherData.value == null) {
            viewModel.fetchData()
        }

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