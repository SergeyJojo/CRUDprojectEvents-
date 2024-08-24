package com.learning.crud_events_practice.data

import kotlinx.coroutines.flow.Flow

class ItemsRepositoryImpl(
    private val
    dao: ItemsDao
) : ItemsRepository {
    override suspend fun insertItem(item: Items) {
        return dao.insertItem(item)
    }

    override suspend fun deleteItem(item: Items) {
        return dao.deleteItem(item)
    }

    override suspend fun getItemById(id: Int): Items? {
        return dao.getItemById(id)
    }

    override fun getAllItems(): Flow<List<Items>> {
        return dao.getAllItems()
    }


}