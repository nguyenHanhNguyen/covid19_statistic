package com.n2n.covid19.ui.main.filter.search

import com.n2n.covid19.model.country.local.CountryDao
import com.n2n.covid19.model.country.local.CountryDbEntity
import javax.inject.Inject

interface SearchRepository {
    fun searchCountry(search: String): List<CountryDbEntity>
}

class Local @Inject constructor(private val countryDao: CountryDao): SearchRepository {

    override fun searchCountry(search: String): List<CountryDbEntity> {
        return countryDao.searchCountry(search)
    }

}