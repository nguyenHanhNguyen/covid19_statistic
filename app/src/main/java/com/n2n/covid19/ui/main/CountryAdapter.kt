package com.n2n.covid19.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n2n.covid19.databinding.ItemCountryBinding
import com.n2n.covid19.model.CountryView

class CountryAdapter(private val countries: List<CountryView>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(countries[position])

    inner class ViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountryView) {
            binding.country = country
            binding.executePendingBindings()
        }
    }
}