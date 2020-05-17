package com.n2n.covid19.ui.main.filter.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n2n.covid19.model.country.local.CountryDbEntity
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private var _countries = MutableLiveData<List<CountryDbEntity>>()
    val countries: LiveData<List<CountryDbEntity>> = _countries

    fun searchCountry(search: String) {
        searchUseCase.searchCountry(search) {
            _countries.value = it
        }
    }
}