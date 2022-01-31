package com.example.livedatas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Chat::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun chatDao(): ChatDao
    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "livedata-database"
                    ).allowMainThreadQueries().
                    fallbackToDestructiveMigration().build()
                }
            }

            return instance
        }
    }
}