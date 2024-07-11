package com.example.countryweather.ui.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countryweather.R
import com.google.android.material.textfield.TextInputEditText

class CountriesListAdapter(private val listener: OnCardClickListener) :
   ListAdapter<Country,CountriesListAdapter.CountriesViewHolder>(CountriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.countrt_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val countryName: TextView = itemView.findViewById<TextInputEditText>(R.id.name)
        private val countryPopulation: TextView = itemView.findViewById<TextInputEditText>(R.id.population)
        private val countryFlag: ImageView = itemView.findViewById(R.id.flagImage)

        fun bind(item:Country){
            val IMG_BASE = "http://www.geognos.com/api/en/countries/flag/"
            countryName.text = item.name
            countryPopulation.text = item.population.toString()
            Glide.with(itemView).load(IMG_BASE + item.code + ".png").override(300, 200)
                .into(countryFlag)
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onCardClick(position)
        }
    }

    class CountriesDiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.id === newItem.id

        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return  return newItem == oldItem
        }


    }

    interface OnCardClickListener {
        fun onCardClick(position: Int)
    }

}
