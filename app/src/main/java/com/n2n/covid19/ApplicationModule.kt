package com.n2n.covid19

import android.content.Context
import com.n2n.covid19.model.CovidRoomDatabase
import com.n2n.covid19.model.country.local.CountryDao
import com.n2n.covid19.model.summary.local.SummaryDao
import com.n2n.covid19.ui.main.filter.search.Local
import com.n2n.covid19.ui.main.filter.search.SearchRepository
import com.n2n.covid19.ui.main.summary.MainRepository
import com.n2n.covid19.ui.main.summary.Network
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_COVID19 = "https://api.covid19api.com/"

@Module
class ApplicationModule (val context: Context){

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

    @Provides
    @Singleton
    fun providesContext() = context

    @Provides
    @Singleton
    fun providesDatabase(): CovidRoomDatabase {
        return CovidRoomDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesCountryDao(covidDatabase: CovidRoomDatabase): CountryDao {
        return covidDatabase.countryDao()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(dataSource: Local): SearchRepository {
        return dataSource
    }

    @Provides
    @Singleton
    fun providesSummaryDao(covidDatabase: CovidRoomDatabase): SummaryDao {
        return covidDatabase.summaryDao()
    }
}