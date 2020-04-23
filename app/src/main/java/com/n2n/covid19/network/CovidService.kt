package com.n2n.covid19.network

import com.n2n.covid19.model.ListCountryApiEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CovidService @Inject constructor(retrofit: Retrofit): CovidApi {

    private val covidApi by lazy { retrofit.create(CovidApi::class.java) }

    override fun getSummaryData() : Call<ListCountryApiEntity> {
        return covidApi.getSummaryData()
    }

}