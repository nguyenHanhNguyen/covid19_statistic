package com.n2n.covid19.model.summary

import com.google.gson.annotations.SerializedName
import com.n2n.covid19.model.summary.local.GlobalDbEntity

class SummaryApiEntity {

    @SerializedName("Global")
    val global: Global? = null

    @SerializedName("Countries")
    val countries: List<SummaryCountryApiEntity>? = null

    fun toSummaryDomain(): SummaryDomain {
        val globalDomain = GlobalDomain(
            global!!.newConfirmed,
            global.totalConfirmed,
            global.newDeath,
            global.totalDeath,
            global.newRecovered,
            global.totalRecovered
        )
        val countryListDomain = countries!!.map {
            it.toSummaryCountryDomain()
        }
        return SummaryDomain(globalDomain, countryListDomain)
    }

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

    fun toGlobalEntity() = GlobalDbEntity("global", newConfirmed, totalConfirmed, newDeath, totalDeath, newRecovered, totalRecovered)
}