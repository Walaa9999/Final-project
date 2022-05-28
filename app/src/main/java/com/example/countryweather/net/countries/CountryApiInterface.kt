package com.example.countryweather.net.countries

import io.reactivex.Single
import retrofit2.http.GET

interface CountryApiInterface {
    @GET("v2/all")
    fun getCountriesList(): Single<List<CountryDataItem>>
}