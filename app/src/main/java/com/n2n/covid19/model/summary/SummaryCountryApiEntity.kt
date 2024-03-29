package com.n2n.covid19.model.summary

import com.google.gson.annotations.SerializedName
import com.n2n.covid19.model.summary.local.SummaryDbEntity

class SummaryCountryApiEntity {

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

    fun toSummaryCountryDomain() = SummaryCountryDomain(
        country,
        newConfirmed,
        totalConfirmed,
        newDeath,
        totalDeath,
        newRecovered,
        totalRecovered,
        date
    )

    fun toSummaryDbEntity() = SummaryDbEntity(
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