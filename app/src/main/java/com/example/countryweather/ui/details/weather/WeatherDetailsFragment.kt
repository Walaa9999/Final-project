package com.example.countryweather.ui.details.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.countryweather.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class WeatherDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_weather_details, container, false)
        val viewPager2 = root.findViewById<ViewPager2>(R.id.viewpager)
        val tabLayout = root.findViewById<TabLayout>(R.id.tab_layout)
        val adapter = ViewPagerAdapter(this.childFragmentManager,lifecycle)
        viewPager2.adapter=adapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text = "Today"
                }
                1->{
                    tab.text = "Tomorrow"
                }
            }
        }.attach()
        return root
    }



}