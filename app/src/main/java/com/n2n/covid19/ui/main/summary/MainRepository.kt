package com.n2n.covid19.ui.main.summary

import android.util.Log
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.functional.Either
import com.n2n.covid19.model.country.CountryApiEntity
import com.n2n.covid19.model.country.local.CountryDao
import com.n2n.covid19.model.country.local.CountryDbEntity
import com.n2n.covid19.model.summary.SummaryApiEntity
import com.n2n.covid19.model.summary.SummaryDomain
import com.n2n.covid19.network.BaseNetwork
import com.n2n.covid19.network.CovidService
import javax.inject.Inject

interface MainRepository {
    fun getSummary(): Either<Failure, SummaryDomain>
    fun getCountries(): Either<Failure, List<CountryDbEntity>>
    fun getCountriesSaveDb()
    fun getCountrySlugFromDb(): List<String>
}

class Network @Inject constructor(private val apiService: CovidService,
                                  private val countryDao: CountryDao) : BaseNetwork(),
    MainRepository {

    override fun getSummary(): Either<Failure, SummaryDomain> {
        return request(apiService.getSummaryData()
            , { it.toSummaryDomain() }
            , SummaryApiEntity())

    }

    override fun getCountries(): Either<Failure, List<CountryDbEntity>> {
        return request(apiService.getCountries()
            , { it.map { apiEntity: CountryApiEntity -> apiEntity.toCountryDbEntity() } }
            , arrayListOf(CountryApiEntity()))
    }

    override fun getCountriesSaveDb() {
        if (getCountrySlugFromDb().isEmpty()) {
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
    }

    private fun insertCountry(countries: List<CountryDbEntity>) {
        countries.forEach {
            countryDao.insert(it)
        }
    }

    override fun getCountrySlugFromDb(): List<String> {
        return countryDao.getAllCountrySlug()
    }
}