package com.n2n.covid19.ui.main.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.n2n.covid19.databinding.ItemCountryBinding
import com.n2n.covid19.model.summary.SummaryCountryView

class CountryAdapter(private val summaries: List<SummaryCountryView>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {

    private var filterList = summaries

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = filterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(filterList[position])

    inner class ViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(summary: SummaryCountryView) {
            binding.country = summary
            binding.executePendingBindings()
            binding.viewPercent.percentDeath = summary.totalDeathRaw.toFloat() / summary.totalConfirmedRaw
            binding.viewPercent.percentRecover = summary.totalRecoveredRaw.toFloat() / summary.totalConfirmedRaw
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(filter: CharSequence?): FilterResults {
                val filterString = filter.toString()
                filterList = if (filterString.isEmpty()) {
                    summaries
                } else {
                    val resultList = ArrayList<SummaryCountryView>()
                    summaries.find { it.country.equals(filterString, ignoreCase = false) }?.let {
                        resultList.add(it)
                    }
                    resultList
                }
                val filterResult = FilterResults()
                filterResult.values = filterList
                return filterResult
            }

            override fun publishResults(filter: CharSequence?, result: FilterResults?) {
                result?.let {
                    filterList = it.values as List<SummaryCountryView>
                }
                notifyDataSetChanged()
            }
        }
    }

}