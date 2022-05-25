package com.example.countryweather.ui.countries

import androidx.recyclerview.widget.DiffUtil

class CountriesDiffCallback(
    private val oldList: ArrayList<Country>,
    private val newList: ArrayList<Country>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name === newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (name, population, code) = oldList[oldPosition]
        val (name2, population2, code2) = newList[newPosition]

        return population == population2 && code == code2 && name == name2
    }

}