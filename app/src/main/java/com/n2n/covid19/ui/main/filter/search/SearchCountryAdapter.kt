package com.n2n.covid19.ui.main.filter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n2n.covid19.databinding.ItemCountrySearchBinding
import com.n2n.covid19.model.country.local.CountryDbEntity

class SearchCountryAdapter : RecyclerView.Adapter<SearchCountryAdapter.ViewHolder>() {

    var countries: ArrayList<CountryDbEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountrySearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(countries[position])

    inner class ViewHolder(private val binding: ItemCountrySearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountryDbEntity) {
            binding.country = country
            binding.executePendingBindings()
        }
    }

}