package com.learning.crud_events_practice.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Items::class],
    version = 1
)
abstract class ItemsDataBase : RoomDatabase() {

    abstract val dao: ItemsDao
}