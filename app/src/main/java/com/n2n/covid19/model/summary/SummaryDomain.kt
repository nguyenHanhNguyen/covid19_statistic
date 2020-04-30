package com.n2n.covid19.model.summary

data class GlobalCountriesDomain(
    val global: GlobalDomain,
    val countriesList: List<SummaryDomain>
)

data class SummaryDomain(
    val country: String,
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeath: Int,
    val totalDeath: Int,
    val newRecovered: Int,
    val totalRecovered: Int,
    val date: String
)

data class GlobalDomain(
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeath: Int,
    val totalDeath: Int,
    val newRecovered: Int,
    val totalRecovered: Int
)