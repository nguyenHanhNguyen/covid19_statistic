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

class RefactorCountryAdapter(private val countries: List<SummaryCountryView>,
                            private val onClick: (SummaryCountryView) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    //1. can this be lateinit ? why, why not
    var onBookmarkClick: OnBookmarkClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ViewHolder(view) {
            onClick(countries[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.countrySummary = country
        //2. should create a bind function in viewholder
        holder.country.text = country.country
        holder.tv_total_confirm.text = country.totalConfirmed
        holder.total_death.text = country.totalDeath
        //3. create click function
//        holder.bookmark.setOnClickListener {
//            onBookmarkClick!!.ClickBookmark(country)
//        }
//        holder.setOnBookMarkClickListener(onClickListener)
    }

    private var onClickListener = View.OnClickListener {
        val countryHolder = it.tag as ViewHolder
        val country = countryHolder.countrySummary
        onBookmarkClick!!.ClickBookmark(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    interface OnBookmarkClick {
        //4. naming
        fun ClickBookmark(country: SummaryCountryView)
    }
}

class ViewHolder(layout: View, onBookmarkClick: (Int) -> Unit) : RecyclerView.ViewHolder(layout) {
    //naming, repetitive findViewById
    //5. data binding
    val country: TextView = layout.findViewById(R.id.tv_country)
    val tv_total_confirm = layout.findViewById<TextView>(R.id.tv_total_confirmed)
    val total_death = layout.findViewById<TextView>(R.id.tv_total_death)
    val bookmark = layout.findViewById<CheckBox>(R.id.imv_bookmark)

    lateinit var countrySummary: SummaryCountryView

    init {
        bookmark.setOnClickListener { onBookmarkClick(adapterPosition) }
    }

    fun setOnBookMarkClickListener(onClickListener: View.OnClickListener) {
        bookmark.tag = this
        bookmark.setOnClickListener(onClickListener)
    }

}

//class ViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
//    //naming, repetitive findViewById
//    //5. data binding
//    val country: TextView = layout.findViewById(R.id.tv_country)
//    val tv_total_confirm = layout.findViewById<TextView>(R.id.tv_total_confirmed)
//    val total_death = layout.findViewById<TextView>(R.id.tv_total_death)
//    val bookmark = layout.findViewById<CheckBox>(R.id.imv_bookmark)
//
//    lateinit var countrySummary : SummaryCountryView
//
//    fun setOnBookMarkClickListener(onClickListener: View.OnClickListener) {
//        bookmark.tag = this
//        bookmark.setOnClickListener(onClickListener)
//    }
//
//}