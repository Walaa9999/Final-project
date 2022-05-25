package com.example.countryweather.ui.countries

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countryweather.R

class CountriesListAdapter(private val listner:OnCardClickListner) :
    RecyclerView.Adapter<CountriesListAdapter.CountriesViewHolder>() {

    private val countriesList = ArrayList<Country>()

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
        Glide.with(holder.itemView).load(IMG_BASE+country.code+".png").override(300, 200).into(holder.countryFlag)


    }

    override fun getItemCount(): Int = countriesList.size

    fun setData(newList:ArrayList<Country>){
        val diffCallback = CountriesDiffCallback(countriesList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        countriesList.clear()
        countriesList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class CountriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var countryName: TextView = itemView.findViewById<TextView>(R.id.name)
        var countruPopulation: TextView = itemView.findViewById<TextView>(R.id.population)
        var countryFlag: ImageView = itemView.findViewById<ImageView>(R.id.flagImage)


        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if(position!=RecyclerView.NO_POSITION)
            listner.onCardClick(position)
        }
    }

    interface OnCardClickListner{
        fun onCardClick(position:Int)
    }

}
