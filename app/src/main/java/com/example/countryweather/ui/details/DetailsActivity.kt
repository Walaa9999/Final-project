package com.example.countryweather.ui.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.countryweather.R
import com.example.countryweather.databinding.ActivityCountryWeatherBinding
import com.example.countryweather.databinding.ActivityDetailsBinding
import com.example.countryweather.ui.details.weather.SharedViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailsBinding
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController = findNavController(R.id.nav_host)
        val topAppBar = binding.topAppBar
        val bottomNavigation = binding.bottomNavigation
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph)

        val name= NavArgument.Builder().setDefaultValue(intent?.extras?.getString("name").toString()).build()
        val population= NavArgument.Builder().setDefaultValue(intent?.extras?.getString("population").toString()).build()
        val region= NavArgument.Builder().setDefaultValue(intent?.extras?.getString("region").toString()).build()
        val capital= NavArgument.Builder().setDefaultValue(intent?.extras?.getString("capital").toString()).build()
        val code= NavArgument.Builder().setDefaultValue(intent?.extras?.getString("code").toString()).build()

        graph.addArgument("name",name)
        graph.addArgument("population",population)
        graph.addArgument("region",region)
        graph.addArgument("code",code)
        graph.addArgument("capital",capital)

        navController.graph=graph


        bottomNavigation.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        topAppBar.setupWithNavController(navController, appBarConfiguration)


        val model = ViewModelProvider(this)[SharedViewModel::class.java]
        model.sendName(intent?.extras?.getString("name").toString())

    }








}