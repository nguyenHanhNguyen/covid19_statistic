package com.n2n.covid19.ui.main

import com.n2n.covid19.exception.Failure
import com.n2n.covid19.functional.Either
import com.n2n.covid19.model.country.CountryDomain
import com.n2n.covid19.model.summary.SummaryDomain
import com.n2n.covid19.model.summary.CountrySummaryApiEntity
import com.n2n.covid19.network.BaseNetwork
import com.n2n.covid19.network.CovidService
import javax.inject.Inject

interface MainRepository {
    fun getCountriesSummary(): Either<Failure, List<SummaryDomain>>
//    fun getCountries(): Either<Failure, List<CountryDomain>>
}

class Network @Inject constructor(private val apiService: CovidService) : BaseNetwork(), MainRepository {

    override fun getCountriesSummary(): Either<Failure, List<SummaryDomain>> {
        return request(apiService.getSummaryData()
            , { it.countries!!.map { countryApiEntity -> countryApiEntity.toSummaryDomain() } }
            , CountrySummaryApiEntity())

    }

//    override fun getCountries(): Either<Failure, List<CountryDomain>> {
//        //TODO: get list of countries and store in local database
//        return request(apiService.getCountries())
//    }
}