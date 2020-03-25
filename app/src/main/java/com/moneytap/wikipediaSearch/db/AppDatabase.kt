package com.moneytap.wikipediaSearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moneytap.wikipediaSearch.db.dao.WikiDao
import com.moneytap.wikipediaSearch.model.WikiItemEntity

@Database(entities = arrayOf(WikiItemEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wikiDao(): WikiDao

    companion object {
        fun getDb(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
        }

    }
}