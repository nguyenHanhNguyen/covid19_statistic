package com.n2n.covid19.model

import com.google.gson.annotations.SerializedName

class CountryApiEntity {

    @SerializedName("Country")
    var country: String = ""

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

    @SerializedName("Date")
    var date: String = ""

    fun toCountryDomain() = CountryDomain(
        country,
        newConfirmed,
        totalConfirmed,
        newDeath,
        totalDeath,
        newRecovered,
        totalRecovered,
        date
    )
}