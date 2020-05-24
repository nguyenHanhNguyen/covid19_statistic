package com.n2n.covid19.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.n2n.covid19.model.country.local.CountryDao
import com.n2n.covid19.model.country.local.CountryDbEntity
import com.n2n.covid19.model.summary.local.GlobalDao
import com.n2n.covid19.model.summary.local.GlobalDbEntity
import com.n2n.covid19.model.summary.local.SummaryDao
import com.n2n.covid19.model.summary.local.SummaryDbEntity
import com.n2n.covid19.util.MIGRATION_1_2
import com.n2n.covid19.util.MIGRATION_2_3

const val DATABASE_NAME = "covid_database"

@Database(entities = [CountryDbEntity::class, SummaryDbEntity::class, GlobalDbEntity::class], version = 3, exportSchema = true)
abstract class CovidRoomDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    abstract fun summaryDao(): SummaryDao

    abstract fun globalDao(): GlobalDao

    companion object {

        @Volatile
        private var INSTANCE: CovidRoomDatabase? = null

        fun getDatabase(context: Context): CovidRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CovidRoomDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}