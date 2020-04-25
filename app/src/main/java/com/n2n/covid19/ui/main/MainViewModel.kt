package com.n2n.covid19.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n2n.covid19.UseCase
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.extension.convertUtcFormat
import com.n2n.covid19.model.CountryDomain
import com.n2n.covid19.model.CountryView
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
                it.newConfirmed.toString(),
                it.totalConfirmed.toString(),
                it.newDeath.toString(),
                it.totalDeath.toString(),
                it.newRecovered.toString(),
                it.totalRecovered.toString(), convertUtcFormat(it.date)
            )
        }
    }

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }


}