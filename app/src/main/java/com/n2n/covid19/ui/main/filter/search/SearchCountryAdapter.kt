package com.n2n.covid19.ui.main.filter.search

import android.graphics.Typeface
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n2n.covid19.R
import com.n2n.covid19.databinding.ItemCountrySearchBinding
import com.n2n.covid19.extension.setSpanText
import com.n2n.covid19.model.country.local.CountryDbEntity

class SearchCountryAdapter : RecyclerView.Adapter<SearchCountryAdapter.ViewHolder>() {

    var countries: ArrayList<CountryDbEntity> = ArrayList()
    val boldStyle = StyleSpan(Typeface.BOLD)
    var spanText = ""

    interface OnItemClick {
        fun onCountryClick(slug: String)
    }

    lateinit var onItemClick: OnItemClick

    private var onClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as ViewHolder
        onItemClick.onCountryClick(viewHolder.country.slug)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountrySearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
        holder.setUpClickListener(onClickListener)
    }

    inner class ViewHolder(private val binding: ItemCountrySearchBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var country: CountryDbEntity

        fun bind(country: CountryDbEntity) {
            this.country = country
            binding.tvCountryName.setSpanText(spanText, country.country, boldStyle, R.color.text_highlight)
        }

        fun setUpClickListener(onClickListener: View.OnClickListener) {
            itemView.tag = this
            itemView.setOnClickListener(onClickListener)
        }

    }

}