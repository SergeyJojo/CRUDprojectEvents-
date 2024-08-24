package com.learning.crud_events_practice.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [Items::class]
)
abstract class ItemsDataBase : RoomDatabase() {

    abstract val dao: ItemsDao
}