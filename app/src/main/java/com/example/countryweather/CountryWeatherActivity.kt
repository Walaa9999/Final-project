package com.example.countryweather

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryweather.ui.countries.CountriesListAdapter
import com.example.countryweather.ui.countries.CountriesViewModel
import com.example.countryweather.ui.countries.Country



class CountryWeatherActivity : AppCompatActivity() {


    private val countriesViewModel by viewModels<CountriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_weather)

        countriesViewModel.loadCountries()

        val list = ArrayList<Country>()
        val recyclerView = findViewById<RecyclerView>(R.id.countriesRecyclerView)
        recyclerView.itemAnimator = DefaultItemAnimator()
        val adapter = CountriesListAdapter()
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

        countriesViewModel.countriesLiveData.observe(this) {
            for (item in it) {
                list.add(Country(item.name, item.population, item.alpha2Code))
            }
            adapter.setData(list)
        }

        findViewById<EditText>(R.id.searchBox).doOnTextChanged { text, _, _, _ ->
            if (text?.length != 0) {
                list.clear()
                countriesViewModel.search(text.toString())

            } else {
                list.clear()
                countriesViewModel.loadCountries()
            }

        }


    }


    override fun onDestroy() {
        countriesViewModel.dispose()
        super.onDestroy()

    }


}
