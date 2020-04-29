package com.n2n.covid19.model.country

import com.google.gson.annotations.SerializedName
import com.n2n.covid19.model.country.local.CountryDbEntity

class CountryApiEntity {

    @SerializedName("Country")
    var country: String = ""

    @SerializedName("Slug")
    var slug: String = ""

    fun toCountryDomain() = CountryDomain(country, slug)

    fun toCountryDbEntity() = CountryDbEntity(country, slug)
}