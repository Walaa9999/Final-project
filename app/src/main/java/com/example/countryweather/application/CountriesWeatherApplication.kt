package com.example.countryweather.application

import android.app.Application
import com.example.countryweather.di.AppComponent
import com.example.countryweather.di.DaggerAppComponent

class CountriesWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}