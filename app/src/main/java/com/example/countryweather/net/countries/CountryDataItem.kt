package com.example.countryweather.net.countries

data class CountryDataItem(
    val capital: String,
    val alpha2Code: String,
    val name: String,
    val population: Int,
    val region: String,
    val latlng: List<Double>,
)