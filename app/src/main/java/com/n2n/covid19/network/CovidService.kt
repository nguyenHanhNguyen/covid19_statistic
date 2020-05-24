package com.n2n.covid19.network

import com.n2n.covid19.model.country.CountryApiEntity
import com.n2n.covid19.model.summary.SummaryApiEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CovidService @Inject constructor(private val covidApi: CovidApi): CovidApi {

    override fun getSummaryData() : Call<SummaryApiEntity> {
        return covidApi.getSummaryData()
    }

    override fun getCountries(): Call<List<CountryApiEntity>> {
        return covidApi.getCountries()
    }

}