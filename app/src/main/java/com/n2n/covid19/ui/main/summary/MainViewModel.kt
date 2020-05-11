package com.n2n.covid19.ui.main.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n2n.covid19.UseCase
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.model.summary.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getSummaryUseCase: GetSummaryUseCase,
                                        private val getCountryUseCase: GetCountryUseCase,
                                        private val getCountryFromDbUseCase: GetCountryFromDbUseCase) : ViewModel() {

    private val _listCountry = MutableLiveData<List<SummaryCountryView>>()
    private val _loading = MutableLiveData<Boolean>()
    private val _global = MutableLiveData<GlobalView>()

    val listSummary: LiveData<List<SummaryCountryView>> = _listCountry
    val global: LiveData<GlobalView> = _global
    val loading: LiveData<Boolean> = _loading

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    var countriesDomain: List<SummaryCountryDomain>? = null

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
//        getCountryUseCase.getAndSaveCountry()
    }

    private fun onGetSummarySuccess(summary: SummaryDomain) {
        viewModelScope.launch {
            notifyViewLoadSuccess(summary.toSummaryView())
        }
        countriesDomain = summary.countriesList
        //notifyViewLoadSuccess(summary.toSummaryView())
        // val countrySlug = getCountryFromDbUseCase.getCountrySlug()
    }

    fun sortByTotalConfirmedDescending() {
        _loading.postValue(true)
        val sortedList = countriesDomain?.sortedByDescending { it.totalConfirmed }
        viewModelScope.launch {
            loadCountryList(sortedList?.map { it.toSummaryCountryView() }!!)
        }
    }

    fun sortByTotalConfirmedAscending() {
        _loading.postValue(true)
        val sortedList = countriesDomain?.sortedBy { it.totalConfirmed }
        viewModelScope.launch {
            loadCountryList(sortedList?.map { it.toSummaryCountryView() }!!)
        }
    }

    fun sortByDeathDescending() {
        _loading.postValue(true)
        val sortedList = countriesDomain?.sortedByDescending { it.totalDeath }
        viewModelScope.launch {
            loadCountryList(sortedList?.map { it.toSummaryCountryView() }!!)
        }
    }

    fun sortByDeathAscending() {
        _loading.postValue(true)
        val sortedList = countriesDomain?.sortedBy { it.totalDeath }
        viewModelScope.launch {
            loadCountryList(sortedList?.map { it.toSummaryCountryView() }!!)
        }
    }

    fun sortByRecoveredDescending() {
        _loading.postValue(true)
        val sortedList = countriesDomain?.sortedByDescending { it.totalRecovered }
        viewModelScope.launch {
            loadCountryList(sortedList?.map { it.toSummaryCountryView() }!!)
        }
    }

    fun sortByRecoveredAscending() {
        _loading.postValue(true)
        val sortedList = countriesDomain?.sortedBy { it.totalRecovered }
        viewModelScope.launch {
            loadCountryList(sortedList?.map { it.toSummaryCountryView() }!!)
        }
    }

    private fun notifyViewLoadSuccess(summaryView: SummaryView) {
        _loading.postValue(false)
        _listCountry.postValue(summaryView.countriesList)
        _global.postValue(summaryView.global)
    }

    private fun loadCountryList(countries: List<SummaryCountryView>) {
        _loading.postValue(false)
        _listCountry.postValue(countries)
    }

    private fun handleFailure(failure: Failure) {
        _loading.value = false
        _failure.value = failure
    }

}