package com.example.businesscard.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BusinessCard::class],
    version = 1,
    exportSchema = false
)
abstract class BusinessCardDatabase : RoomDatabase() {

    abstract fun getBusinessCardDao(): BusinessCardDao

    companion object {
        @Volatile
        private var instance: BusinessCardDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BusinessCardDatabase::class.java,
                "business_card.db"
            ).build()
    }
}