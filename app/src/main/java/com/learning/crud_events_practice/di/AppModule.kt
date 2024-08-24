package com.learning.crud_events_practice.di

import android.app.Application
import androidx.room.Room
import com.learning.crud_events_practice.data.ItemsDataBase
import com.learning.crud_events_practice.data.ItemsRepository
import com.learning.crud_events_practice.data.ItemsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemsDatabase(app: Application): ItemsDataBase {
        return Room.databaseBuilder(
            app,
            ItemsDataBase::class.java,
            "Items_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemsRepository(db: ItemsDataBase): ItemsRepository {
        return ItemsRepositoryImpl(db.dao)
    }



}