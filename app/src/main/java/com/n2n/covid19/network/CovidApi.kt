package com.n2n.covid19.network

import com.n2n.covid19.model.ListCountryApiEntity
import retrofit2.Call
import retrofit2.http.GET

interface CovidApi {

    @GET("summary")
    fun getSummaryData() : Call<ListCountryApiEntity>
}