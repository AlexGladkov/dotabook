package com.agladkov.dotabook.di

import android.content.Context
import androidx.room.RoomDatabase
import com.agladkov.dotabook.data.storage.RoomAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): RoomAppDatabase = RoomAppDatabase.buildDataSource(context = context)
}