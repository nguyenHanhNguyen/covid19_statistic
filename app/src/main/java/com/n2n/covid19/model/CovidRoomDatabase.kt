package com.n2n.covid19.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.n2n.covid19.model.country.local.CountryDao
import com.n2n.covid19.model.country.local.CountryDbEntity
import com.n2n.covid19.model.summary.local.SummaryDao
import com.n2n.covid19.model.summary.local.SummaryDbEntity

const val DATABASE_NAME = "covid_database"

@Database(entities = [CountryDbEntity::class, SummaryDbEntity::class], version = 2, exportSchema = false)
abstract class CovidRoomDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    abstract fun summaryDao(): SummaryDao

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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}