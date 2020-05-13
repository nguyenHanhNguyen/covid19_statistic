package com.n2n.covid19.ui.main.filter.search

import com.n2n.covid19.model.country.local.CountryDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    fun searchCountry(search: String, onResult: (List<CountryDbEntity>) -> Unit) {
        val job = GlobalScope.async(Dispatchers.IO) { searchRepository.searchCountry(search) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }
}