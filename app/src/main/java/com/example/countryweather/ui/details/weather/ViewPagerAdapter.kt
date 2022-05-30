package com.example.countryweather.ui.details.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryweather.R
import com.google.android.material.textfield.TextInputEditText


class ViewPagerAdapter :
    ListAdapter<WeatherData, ViewPagerAdapter.ViewPagerHolder>(ViewPagerDiffCallback()){
    inner class ViewPagerHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val date = itemView.findViewById<TextInputEditText>(R.id.date)
        private val temp = itemView.findViewById<TextInputEditText>(R.id.tempreature)
        private val pressure = itemView.findViewById<TextInputEditText>(R.id.pressure)
        private val hum = itemView.findViewById<TextInputEditText>(R.id.hum)

        fun bind(item:WeatherData){
            date.setText(item.date)
            temp.setText(item.temp)
            pressure.setText(item.pressure)
            hum.setText(item.hum)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewPagerDiffCallback : DiffUtil.ItemCallback<WeatherData>() {
        override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem.date === newItem.date
        }

        override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem==newItem
        }


    }


}