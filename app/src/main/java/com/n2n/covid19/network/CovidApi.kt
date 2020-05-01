package com.n2n.covid19.network

import com.n2n.covid19.model.country.CountryApiEntity
import com.n2n.covid19.model.summary.SummaryApiEntity
import retrofit2.Call
import retrofit2.http.GET

interface CovidApi {

    @GET("summary")
    fun getSummaryData() : Call<SummaryApiEntity>

    @GET("countries")
    fun getCountries() : Call<List<CountryApiEntity>>
}