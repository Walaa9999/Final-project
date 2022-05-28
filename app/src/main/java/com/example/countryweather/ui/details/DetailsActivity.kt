package com.example.countryweather.ui.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.countryweather.R
import com.example.countryweather.ui.details.weather.SharedViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText


class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar.setNavigationOnClickListener {
            finish()
        }
        val navController = findNavController(R.id.nav_host_fragment_container)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setupWithNavController(navController)
        loadCountryDetails()

        val model = ViewModelProvider(this).get(SharedViewModel::class.java)
        model.sendName( intent?.extras?.getString("name").toString())



    }



    private fun loadCountryDetails(){
        val IMG_BASE = "http://www.geognos.com/api/en/countries/flag/"
        findViewById<TextInputEditText>(R.id.country_name).setText(
            intent?.extras?.getString("name").toString()
        )
        findViewById<TextInputEditText>(R.id.count_capital).setText(
            intent?.extras?.getString("capital").toString()
        )
        findViewById<TextInputEditText>(R.id.count_population).setText(
            intent?.extras?.getString("population").toString()
        )
        findViewById<TextInputEditText>(R.id.count_region).setText(
            intent?.extras?.getString("region").toString()
        )
        Glide.with(this).load(IMG_BASE + intent?.extras?.getString("code").toString() + ".png")
            .override(200, 100).into(findViewById(R.id.flagImage))
    }
}