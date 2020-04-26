package com.n2n.covid19.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n2n.covid19.UseCase
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.extension.convertUtcFormat
import com.n2n.covid19.model.summary.CountryDomain
import com.n2n.covid19.model.summary.CountryView
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getSummaryUseCase: GetSummaryUseCase) :
    ViewModel() {

    var listCountry = MutableLiveData<List<CountryView>>()

    var failure: MutableLiveData<Failure> = MutableLiveData()

    fun getCountrySummary() {
        getSummaryUseCase(UseCase.None()) {
            it.either(
                ::handleFailure,
                ::onGetCountryDomain
            )
        }
    }

    private fun onGetCountryDomain(countries: List<CountryDomain>) {
        listCountry.value = countries.map {
            CountryView(
                it.country,
                String.format("%,d", it.newConfirmed),
                String.format("%,d", it.totalConfirmed),
                String.format("%,d", it.newDeath),
                String.format("%,d", it.totalDeath),
                String.format("%,d", it.newRecovered),
                it.totalRecovered.toString(), convertUtcFormat(it.date)
            )
        }
    }

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }


}