package com.n2n.covid19.model.country.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: CountryDbEntity)

    @Query("SELECT country from country_table")
    fun getAllCountryName(): List<String>

    @Query("SELECT slug from country_table")
    fun getAllCountrySlug(): List<String>

    @Query("SELECT * from country_table WHERE country LIKE '%' || :search || '%'")
    fun searchCountry(search: String): List<CountryDbEntity>
}