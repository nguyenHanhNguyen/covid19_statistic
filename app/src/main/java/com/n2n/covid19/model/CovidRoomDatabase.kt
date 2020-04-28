package com.n2n.covid19.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.n2n.covid19.model.country.local.CountryDao
import com.n2n.covid19.model.country.local.CountryDbEntity

@Database(entities = arrayOf(CountryDbEntity::class), version = 1, exportSchema = false)
abstract class CovidRoomDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {

        @Volatile
        private var INSTANCE: CovidRoomDatabase? = null

        fun getDatabase(context: Context) : CovidRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CovidRoomDatabase::class.java,
                    "covid_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}