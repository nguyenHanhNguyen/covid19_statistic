package com.n2n.covid19.ui.main.summary

import com.n2n.covid19.UseCase
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.functional.Either
import com.n2n.covid19.model.summary.SummaryDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(private val mainRepository: MainRepository) : UseCase<SummaryDomain, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, SummaryDomain> {
        return mainRepository.getSummary()
    }
}

class GetCountryUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getAndSaveCountry() {
        GlobalScope.async(Dispatchers.IO) { mainRepository.getCountriesSaveDb() }
    }
}