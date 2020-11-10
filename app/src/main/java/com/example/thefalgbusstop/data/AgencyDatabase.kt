package com.example.thefalgbusstop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ChoferEntity::class], version = 1)
@TypeConverters(ListStringConverters::class)
abstract class AgencyDatabase : RoomDatabase() {

    //region Abstract Methods

    abstract fun agencyDao(): AgencyDao

    //endregion

    //region Companion Object

    companion object {

        private const val DATABASE_NAME = "the_flag_agency_db"

        @Synchronized
        fun getDatabase(context: Context): AgencyDatabase = Room.databaseBuilder(
            context.applicationContext,
            AgencyDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    //endregion

}