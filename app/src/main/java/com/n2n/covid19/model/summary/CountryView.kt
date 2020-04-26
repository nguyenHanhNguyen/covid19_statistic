package com.n2n.covid19.model.summary

data class CountryView(
    val country: String,
    val newConfirmed: String,
    val totalConfirmed: String,
    val newDeath: String,
    val totalDeath: String,
    val newRecovered: String,
    val totalRecovered: String,
    val date: String
)