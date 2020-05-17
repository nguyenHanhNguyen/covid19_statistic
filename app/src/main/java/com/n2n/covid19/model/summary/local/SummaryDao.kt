package com.n2n.covid19.model.summary.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(summary: SummaryDbEntity)

    @Query("SELECT * from summary_table")
    fun getAllCountrySummary() : List<SummaryDbEntity>
}