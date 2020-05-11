package com.n2n.covid19.ui.main.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n2n.covid19.databinding.ItemCountryBinding
import com.n2n.covid19.model.summary.SummaryCountryView

class CountryAdapter(private val summaries: List<SummaryCountryView>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = summaries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(summaries[position])

    inner class ViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(summary: SummaryCountryView) {
            binding.country = summary
            binding.executePendingBindings()
        }
    }
}