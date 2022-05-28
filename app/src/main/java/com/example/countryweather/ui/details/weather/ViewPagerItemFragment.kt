package com.example.countryweather.ui.details.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.countryweather.R
import com.example.countryweather.ui.countries.CountriesViewModel

class ViewPagerItemFragment(val index:Int) : Fragment() {

    private val weatherViewModel by viewModels<WeatherViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_view_pager_item, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        model.name.observe(viewLifecycleOwner){
            weatherViewModel.name = it
            Log.i("request",it.toString())
            weatherViewModel.getWeatherForTwoDays()
        }

        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner){
            Log.i("response",it.toString())
        }
    }
}