package com.example.countryweather.di

import com.example.countryweather.net.countries.CountryApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val BASE_URL = "https://restcountries.com/"

    @Singleton
    @Provides
    fun getRetrofitServiceInterface():CountryApiInterface{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountryApiInterface::class.java)
    }
}