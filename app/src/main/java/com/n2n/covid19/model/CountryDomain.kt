package com.n2n.covid19.model

data class CountryDomain(
    val country: String,
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeath: Int,
    val totalDeath: Int,
    val newRecovered: Int,
    val totalRecovered: Int,
    val date: String
)