package com.n2n.covid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n2n.covid19.UseCase
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.extension.convertUtcFormat
import com.n2n.covid19.model.summary.SummaryDomain
import com.n2n.covid19.model.summary.SummaryView
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getSummaryUseCase: GetSummaryUseCase) : ViewModel() {

    private val _listCountry = MutableLiveData<List<SummaryView>>()
    private val _loading = MutableLiveData<Boolean>()

    val listSummary: LiveData<List<SummaryView>> = _listCountry
    val loading: LiveData<Boolean> = _loading

    private val _failure: MutableLiveData<Failure> = MutableLiveData()

    init {
        getCountrySummary()
    }

    private fun getCountrySummary() {
        _loading.postValue(true)
        getSummaryUseCase(UseCase.None()) {
            it.either(
                ::handleFailure,
                ::onGetCountrySuccess
            )
        }
    }

    private fun onGetCountrySuccess(countries: List<SummaryDomain>) {
        _loading.value = false
        _listCountry.postValue(countries.map {
            SummaryView(
                it.country,
                String.format("%,d", it.newConfirmed),
                String.format("%,d", it.totalConfirmed),
                String.format("%,d", it.newDeath),
                String.format("%,d", it.totalDeath),
                String.format("%,d", it.newRecovered),
                String.format("%,d", it.totalRecovered),
                convertUtcFormat(it.date)
            )
        })
    }

    private fun handleFailure(failure: Failure) {
        _loading.value = false
        _failure.value = failure
    }

}