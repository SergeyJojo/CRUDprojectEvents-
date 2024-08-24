package com.learning.crud_events_practice.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun insertItem(item: Items)

    suspend fun deleteItem(item: Items)

    suspend fun getItemById(id: Int): Items?

    fun getAllItems(): Flow<List<Items>>

}