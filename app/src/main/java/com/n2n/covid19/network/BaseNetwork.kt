package com.n2n.covid19.network

import com.n2n.covid19.exception.Failure
import com.n2n.covid19.functional.Either
import retrofit2.Call

open class BaseNetwork {

    fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }

}