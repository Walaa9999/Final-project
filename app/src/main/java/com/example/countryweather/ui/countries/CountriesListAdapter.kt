package com.example.countryweather.ui.countries

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countryweather.R

class CountriesListAdapter(var countriesList:List<Country>) :
    RecyclerView.Adapter<CountriesListAdapter.CountriesViewHolder>() {

    inner class CountriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var countryName = itemView.findViewById<TextView>(R.id.name)
        var countruPopulation= itemView.findViewById<TextView>(R.id.population)
        var countryFlag = itemView.findViewById<ImageView>(R.id.flagImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.countrt_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val IMG_BASE = "http://www.geognos.com/api/en/countries/flag/"
        val country = countriesList[position]
        holder.countryName.text = country.name
        holder.countruPopulation.text = country.population.toString()
        Glide.with(holder.itemView).load(IMG_BASE+country.code+".png").into(holder.countryFlag)

    }

    override fun getItemCount(): Int = countriesList.size

}
