package com.n2n.covid19.model.summary

data class SummaryView(
    val global: GlobalView,
    val countriesList: List<SummaryCountryView>
)

data class SummaryCountryView(
    val country: String,
    val newConfirmed: String,
    val totalConfirmed: String,
    val newDeath: String,
    val totalDeath: String,
    val newRecovered: String,
    val totalRecovered: String,
    val date: String,
    val totalDeathRaw: Int,
    val totalRecoveredRaw: Int,
    val totalConfirmedRaw: Int
)

data class GlobalView(
    val newConfirmed: String,
    val totalConfirmed: String,
    val newDeath: String,
    val totalDeath: String,
    val newRecovered: String,
    val totalRecovered: String
)