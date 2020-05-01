package com.n2n.covid19.model.summary

import com.n2n.covid19.extension.convertUtcFormat

data class SummaryDomain(
    val global: GlobalDomain,
    val countriesList: List<SummaryCountryDomain>
) {
    fun toSummaryView() : SummaryView{
        val globalView = global.toGlobalView()
        val countriesView = countriesList.map { it.toSummaryCountryView() }
        return SummaryView(globalView, countriesView)
    }
}

data class SummaryCountryDomain(
    val country: String,
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeath: Int,
    val totalDeath: Int,
    val newRecovered: Int,
    val totalRecovered: Int,
    val date: String
) {
    fun toSummaryCountryView() = SummaryCountryView(
        country,
        String.format("%,d", newConfirmed),
        String.format("%,d", totalConfirmed),
        String.format("%,d", newDeath),
        String.format("%,d", totalDeath),
        String.format("%,d", newRecovered),
        String.format("%,d", totalRecovered),
        convertUtcFormat(date)
    )
}

data class GlobalDomain(
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeath: Int,
    val totalDeath: Int,
    val newRecovered: Int,
    val totalRecovered: Int
) {
    fun toGlobalView() = GlobalView(
        String.format("%,d", newConfirmed),
        String.format("%,d", totalConfirmed),
        String.format("%,d", newDeath),
        String.format("%,d", totalDeath),
        String.format("%,d", newRecovered),
        String.format("%,d", totalRecovered)
    )
}