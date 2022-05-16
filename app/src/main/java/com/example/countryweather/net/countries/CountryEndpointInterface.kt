package com.example.countryweather.net.countries

import retrofit2.Call
import retrofit2.http.GET

interface CountryEndpointInterface {
    @GET("v2/all")
    fun getCountriesList():Call<List<CountryDataItem>>
}