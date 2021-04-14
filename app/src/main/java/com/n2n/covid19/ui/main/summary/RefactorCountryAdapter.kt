package com.n2n.covid19.ui.main.summary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.n2n.covid19.R
import com.n2n.covid19.model.summary.SummaryCountryView

class RefactorCountryAdapter(private val countries: List<SummaryCountryView>) : RecyclerView.Adapter<ViewHolder>() {

    var onBookmarkClick: OnBookmarkClick ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.country.text = country.country
        holder.tv_total_confirm.text = country.totalConfirmed
        holder.total_death.text = country.totalDeath
        holder.bookmark.setOnClickListener {
            onBookmarkClick!!.ClickBookmark(country)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    interface OnBookmarkClick{
        fun ClickBookmark(country: SummaryCountryView)
    }
}

class ViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
    val country: TextView = layout.findViewById(R.id.tv_country)
    val tv_total_confirm = layout.findViewById<TextView>(R.id.tv_total_confirmed)
    val total_death = layout.findViewById<TextView>(R.id.tv_total_death)
    val bookmark = layout.findViewById<CheckBox>(R.id.imv_bookmark)
}