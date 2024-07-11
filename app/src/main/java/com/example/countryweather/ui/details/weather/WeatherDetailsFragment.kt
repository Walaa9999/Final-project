package com.example.countryweather.ui.details.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.countryweather.R
import com.example.countryweather.databinding.FragmentCountryDetailsBinding
import com.example.countryweather.databinding.FragmentWeatherDetailsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class WeatherDetailsFragment : Fragment() {

    private val weatherViewModel by viewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = ArrayList<WeatherData>()
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val adapter = ViewPagerAdapter()
        val model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        model.name.observe(viewLifecycleOwner){
            weatherViewModel.name = it
            weatherViewModel.getWeatherForTwoDays()
        }

        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Today"
                }
                1 -> {
                    tab.text = "Tomorrow"
                }
            }
        }.attach()
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner) {
            val current = LocalDateTime.now()
            val tomorrow = current.plusDays(1)
            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
            list.add(
                WeatherData(
                    current.format(formatter),
                    "${it.list[0].temp.min} - ${it.list[0].temp.max}",
                    it.list[0].humidity.toString(),
                    it.list[0].pressure.toString()

                )
            )
            list.add(
                WeatherData(
                    tomorrow.format(formatter),
                    "${it.list[1].temp.min} - ${it.list[1].temp.max}",
                    it.list[1].humidity.toString(),
                    it.list[1].pressure.toString()

                )
            )
            adapter.submitList(list)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        weatherViewModel.dispose()
    }


}