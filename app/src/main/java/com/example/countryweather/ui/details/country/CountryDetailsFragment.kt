package com.example.countryweather.ui.details.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgument
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.countryweather.R
import com.example.countryweather.databinding.FragmentCountryDetailsBinding

class CountryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val IMG_BASE = "http://www.geognos.com/api/en/countries/flag/"
        super.onViewCreated(view, savedInstanceState)
        binding.countryName.setText(arguments?.get("name").toString())
        binding.countCapital.setText(arguments?.get("capital").toString())
        binding.countPopulation.setText(arguments?.get("population").toString())
        binding.countRegion.setText(arguments?.get("region").toString())
        Glide.with(this).load(IMG_BASE + arguments?.get("code") + ".png")
            .override(200, 100).into(binding.flagImage)


//        val action =
//            CountryDetailsFragmentDirections.actionCountryDetailsFragmentToWeatherDetailsFragment(
//                arguments?.get("name").toString()
//            )
//        findNavController().navigate(action)

    }

}