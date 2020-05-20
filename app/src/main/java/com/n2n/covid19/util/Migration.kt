package com.n2n.covid19.util

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `summary_table` (`country` TEXT NOT NULL, `new_confirmed` INTEGER NOT NULL," +
                    " `total_confirmed` INTEGER NOT NULL, `new_deaths` INTEGER NOT NULL," +
                    " `total_deaths` INTEGER NOT NULL, `new_recovered` INTEGER NOT NULL, " +
                    "`total_recovered` INTEGER NOT NULL, `date` TEXT NOT NULL, PRIMARY KEY(`country`))"
        )
    }

}
