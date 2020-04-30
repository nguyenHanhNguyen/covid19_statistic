package com.n2n.covid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n2n.covid19.UseCase
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.extension.convertUtcFormat
import com.n2n.covid19.model.summary.GlobalCountriesDomain
import com.n2n.covid19.model.summary.GlobalView
import com.n2n.covid19.model.summary.SummaryDomain
import com.n2n.covid19.model.summary.SummaryView
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getSummaryUseCase: GetSummaryUseCase,
                                        private val getCountryUseCase: GetCountryUseCase,
                                        private val getCountryFromDbUseCase: GetCountryFromDbUseCase) : ViewModel() {

    private val _listCountry = MutableLiveData<List<SummaryView>>()
    private val _loading = MutableLiveData<Boolean>()
    private val _global = MutableLiveData<GlobalView>()

    val listSummary: LiveData<List<SummaryView>> = _listCountry
    val global: LiveData<GlobalView> = _global
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
                ::onGetSummarySuccess
            )
        }
        getCountryUseCase.getAndSaveCountry()
    }

    private fun onGetSummarySuccess(summary: GlobalCountriesDomain) {
        _loading.value = false
        _listCountry.postValue(summary.countriesList.map {
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

        summary.global.let {
            _global.postValue(
                GlobalView(
                    String.format("%,d", it.newConfirmed),
                    String.format("%,d", it.totalConfirmed),
                    String.format("%,d", it.newDeath),
                    String.format("%,d", it.totalDeath),
                    String.format("%,d", it.newRecovered),
                    String.format("%,d", it.totalRecovered)
                )
            )
        }
        // val countrySlug = getCountryFromDbUseCase.getCountrySlug()
    }

    private fun handleFailure(failure: Failure) {
        _loading.value = false
        _failure.value = failure
    }

}