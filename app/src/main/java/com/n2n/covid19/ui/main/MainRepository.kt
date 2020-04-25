package com.n2n.covid19.ui.main

import com.n2n.covid19.exception.Failure
import com.n2n.covid19.functional.Either
import com.n2n.covid19.model.CountryDomain
import com.n2n.covid19.model.ListCountryApiEntity
import com.n2n.covid19.network.BaseNetwork
import com.n2n.covid19.network.CovidService
import javax.inject.Inject

interface MainRepository {
    fun getCountriesSummary() : Either<Failure, List<CountryDomain>>
}

class Network @Inject constructor(private val apiService: CovidService) : BaseNetwork(), MainRepository {

    override fun getCountriesSummary() : Either<Failure, List<CountryDomain>> {
        return request(apiService.getSummaryData()
            , { it.countries!!.map { countryApiEntity -> countryApiEntity.toCountryDomain() } }
            , ListCountryApiEntity())

    }
}