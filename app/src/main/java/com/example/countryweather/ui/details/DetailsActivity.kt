package com.example.countryweather.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.countryweather.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val navController = findNavController(R.id.nav_host_fragment_container)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setupWithNavController(navController)

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