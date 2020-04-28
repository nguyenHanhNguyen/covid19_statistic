package com.n2n.covid19.model.country.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: CountryDbEntity)

    @Query("SELECT * from country_table")
    fun getAllCountry(): List<CountryDbEntity>
}