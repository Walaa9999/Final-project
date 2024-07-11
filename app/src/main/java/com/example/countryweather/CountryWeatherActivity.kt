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
import com.example.countryweather.databinding.ActivityCountryWeatherBinding
import com.example.countryweather.ui.countries.CountriesListAdapter
import com.example.countryweather.ui.countries.CountriesViewModel
import com.example.countryweather.ui.countries.Country
import com.example.countryweather.ui.details.DetailsActivity


class CountryWeatherActivity : AppCompatActivity(), CountriesListAdapter.OnCardClickListener {


    private val countriesViewModel by viewModels<CountriesViewModel>()
    private val list = ArrayList<Country>()
    private lateinit var binding: ActivityCountryWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        countriesViewModel.loadCountries()

        val recyclerView = binding.countriesRecyclerView
        val adapter = CountriesListAdapter(this)
        val manager = LinearLayoutManager(this)
        var id = 0

        countriesViewModel.countriesLiveData.observe(this) {
            list.clear()
            id = 0
            for (item in it) {
                list.add(
                    Country(
                        id,
                        item.capital,
                        item.alpha2Code,
                        item.name,
                        item.population,
                        item.region,
                    )
                )
                id++
            }
            Log.i("list",list.toString())
            adapter.submitList(list)


        }
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()

       binding.searchBox.doOnTextChanged { text, _, _, _ ->
            if (text?.length != 0) {
                countriesViewModel.search(text.toString())
            } else {
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