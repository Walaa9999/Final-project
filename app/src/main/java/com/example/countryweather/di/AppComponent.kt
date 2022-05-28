package com.example.countryweather.di

import com.example.countryweather.ui.countries.CountriesViewModel
import com.example.countryweather.ui.details.weather.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {

    fun inject(activity: CountriesViewModel)
    fun inject(activity: WeatherViewModel)

}