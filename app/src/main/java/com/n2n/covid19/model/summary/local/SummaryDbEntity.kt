package com.n2n.covid19.model.summary.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summary_table")
data class SummaryDbEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "new_confirmed")
    var newConfirmed: Int,

    @ColumnInfo(name = "total_confirmed")
    var totalConfirmed: Int,

    @ColumnInfo(name = "new_deaths")
    var newDeath: Int,

    @ColumnInfo(name = "total_deaths")
    var totalDeath: Int,

    @ColumnInfo(name = "new_recovered")
    var newRecovered: Int,

    @ColumnInfo(name = "total_recovered")
    var totalRecovered: Int,

    @ColumnInfo(name = "date")
    var date: String

)