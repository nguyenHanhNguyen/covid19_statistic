package com.n2n.covid19.model.summary.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.n2n.covid19.model.summary.GlobalDomain

@Entity(tableName = "global_table")
data class GlobalDbEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey
    var name: String,

    @ColumnInfo(name = "new_confirmed")
    var newConfirmed: Int,

    @ColumnInfo(name = "total_confirmed")
    var totalConfirmed: Int,

    @ColumnInfo(name = "new_death")
    var newDeath: Int,

    @ColumnInfo(name = "total_death")
    var totalDeath: Int,

    @ColumnInfo(name = "new_recovered")
    var newRecovered: Int,

    @ColumnInfo(name = "total_recovered")
    var totalRecovered: Int
) {

    fun toGlobalDomain() = GlobalDomain(
        newConfirmed,
        totalConfirmed,
        newDeath,
        totalDeath,
        newRecovered,
        totalRecovered
    )
}