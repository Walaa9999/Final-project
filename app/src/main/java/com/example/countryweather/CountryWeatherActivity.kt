package com.example.countryweather

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.countryweather.ui.details.DetailsActivity


class CountryWeatherActivity : AppCompatActivity(), CountriesListAdapter.OnCardClickListner {


    private val countriesViewModel by viewModels<CountriesViewModel>()
    private val list = ArrayList<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_weather)
        countriesViewModel.loadCountries()

        val recyclerView = findViewById<RecyclerView>(R.id.countriesRecyclerView)
        val adapter = CountriesListAdapter(this)
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()


        countriesViewModel.countriesLiveData.observe(this) {
            for (item in it) {
                list.add(
                    Country(
                        item.capital,
                        item.alpha2Code,
                        item.name,
                        item.population,
                        item.region,
                        item.latlng
                    )
                )
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

    override fun onCardClick(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("code", list[position].code)
        intent.putExtra("name", list[position].name)
        intent.putExtra("population", list[position].population.toString())
        intent.putExtra("region", list[position].region)
        intent.putExtra("capital", list[position].capital)
        this.startActivity(intent)

    }


}
