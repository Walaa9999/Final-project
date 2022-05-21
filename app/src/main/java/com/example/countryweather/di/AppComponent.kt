package com.example.countryweather.di

import com.example.countryweather.CountryWeatherActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {

    fun inject(activity: CountryWeatherActivity)
}