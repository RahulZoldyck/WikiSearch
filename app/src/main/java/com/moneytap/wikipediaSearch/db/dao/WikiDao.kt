package com.moneytap.wikipediaSearch.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.moneytap.wikipediaSearch.model.WikiItemEntity

@Dao
interface WikiDao {
    @Query("SELECT * FROM WikiItemEntity")
    fun getAll(): List<WikiItemEntity>

    @Insert
    fun insertAll(wikiItem: List<WikiItemEntity>)

    @Delete
    fun delete(item: WikiItemEntity)
}