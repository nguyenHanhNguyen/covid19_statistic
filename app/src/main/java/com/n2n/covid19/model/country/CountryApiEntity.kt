package com.n2n.covid19.model.country

import com.google.gson.annotations.SerializedName

class CountryApiEntity {

    @SerializedName("Country")
    var country: String = ""

    @SerializedName("Slug")
    var slug: String = ""

    fun toCountryDomain() = CountryDomain(country, slug)
}