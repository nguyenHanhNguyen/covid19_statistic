package com.n2n.covid19.model.country.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
class CountryDbEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country")
    var country: String = ""

    @ColumnInfo(name = "slug")
    var slug: String = ""
}