package com.example.countryweather.di

import com.example.countryweather.net.countries.CountryApiInterface
import com.example.countryweather.net.weather.WeatherApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val BASE_URL = "https://restcountries.com/"
    private val BASE_URL2 = "https://api.openweathermap.org/"

    @Singleton
    @Provides
    fun getRetrofitServiceInterface(): CountryApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountryApiInterface::class.java)
    }


    @Singleton
    @Provides
    fun getRetrofitServiceWeatherInterface(): WeatherApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApiInterface::class.java)
    }
}