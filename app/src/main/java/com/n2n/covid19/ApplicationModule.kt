package com.n2n.covid19

import com.n2n.covid19.ui.main.MainRepository
import com.n2n.covid19.ui.main.Network
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_COVID19 = "https://api.covid19api.com/"

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun providesApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_COVID19)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun providesMainRepository(dataSource: Network): MainRepository {
        return dataSource
    }

}