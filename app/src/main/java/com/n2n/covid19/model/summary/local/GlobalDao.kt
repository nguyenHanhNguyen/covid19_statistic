package com.n2n.covid19.model.summary.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GlobalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(global: GlobalDbEntity)

    @Query("SELECT * FROM global_table")
    fun getGlobal() : GlobalDbEntity

}