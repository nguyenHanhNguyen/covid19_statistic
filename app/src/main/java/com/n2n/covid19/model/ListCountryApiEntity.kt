package com.n2n.covid19.model

import com.google.gson.annotations.SerializedName

class ListCountryApiEntity {

    @SerializedName("Countries")
    val countries: List<CountryApiEntity> ?= null

}