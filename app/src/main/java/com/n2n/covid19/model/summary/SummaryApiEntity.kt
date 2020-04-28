package com.n2n.covid19.model.summary

import com.google.gson.annotations.SerializedName

class CountrySummaryApiEntity {

    @SerializedName("Global")
    val global: Global? = null

    @SerializedName("Countries")
    val countries: List<SummaryCountryApiEntity>? = null

}

class Global {
    @SerializedName("NewConfirmed")
    var newConfirmed: Int = 0

    @SerializedName("TotalConfirmed")
    var totalConfirmed: Int = 0

    @SerializedName("NewDeaths")
    var newDeath: Int = 0

    @SerializedName("TotalDeaths")
    var totalDeath: Int = 0

    @SerializedName("NewRecovered")
    var newRecovered: Int = 0

    @SerializedName("TotalRecovered")
    var totalRecovered: Int = 0
}