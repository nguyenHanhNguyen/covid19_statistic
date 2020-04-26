package com.n2n.covid19.ui.main

import com.n2n.covid19.UseCase
import com.n2n.covid19.exception.Failure
import com.n2n.covid19.functional.Either
import com.n2n.covid19.model.summary.CountryDomain
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(private val mainRepository: MainRepository): UseCase<List<CountryDomain>, UseCase.None>(){

    override suspend fun run(params: None): Either<Failure, List<CountryDomain>> {
        return mainRepository.getCountriesSummary()
    }
}