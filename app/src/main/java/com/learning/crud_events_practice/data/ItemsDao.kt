package com.learning.crud_events_practice.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Items)

    @Delete
    suspend fun deleteItem(item: Items)

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItemById(id: Int): Items?

    @Query("SELECT * FROM  items")
    fun getAllItems(): Flow<List<Items>>


}