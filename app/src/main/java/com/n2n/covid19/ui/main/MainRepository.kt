package com.n2n.covid19.ui.main

import android.util.Log
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.functional.Either
import com.n2n.covid19.model.country.CountryApiEntity
import com.n2n.covid19.model.country.local.CountryDao
import com.n2n.covid19.model.country.local.CountryDbEntity
import com.n2n.covid19.model.summary.CountrySummaryApiEntity
import com.n2n.covid19.model.summary.SummaryDomain
import com.n2n.covid19.network.BaseNetwork
import com.n2n.covid19.network.CovidService
import javax.inject.Inject

interface MainRepository {
    fun getCountriesSummary(): Either<Failure, List<SummaryDomain>>
    fun getCountries(): Either<Failure, List<CountryDbEntity>>
    fun getCountriesSaveDb()
}

class Network @Inject constructor(private val apiService: CovidService,
                                  private val countryDao: CountryDao) : BaseNetwork(), MainRepository {

    override fun getCountriesSummary(): Either<Failure, List<SummaryDomain>> {
        return request(apiService.getSummaryData()
            , { it.countries!!.map { countryApiEntity -> countryApiEntity.toSummaryDomain() } }
            , CountrySummaryApiEntity())

    }

    override fun getCountries(): Either<Failure, List<CountryDbEntity>> {
        return request(apiService.getCountries()
            , { it.map { apiEntity: CountryApiEntity -> apiEntity.toCountryDbEntity() } }
            , arrayListOf(CountryApiEntity()))
    }

    override fun getCountriesSaveDb() {
        val response = apiService.getCountries().execute()
        when {
            response.isSuccessful -> {
                response.body()?.map { it.toCountryDbEntity() }?.let {
                    insertCountry(it)
                }
            }
            else -> {
                Log.e("MainRepository", "Error")
            }
        }
    }

    private fun insertCountry(countries: List<CountryDbEntity>) {
        countries.forEach {
            countryDao.insert(it)
        }
    }
}