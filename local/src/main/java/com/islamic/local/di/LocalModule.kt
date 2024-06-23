package com.islamic.local.di

import com.islamic.local.database.dao.PrayDao
import com.islamic.local.localdatasource.pray.IPrayLocalDataSource
import com.islamic.local.localdatasource.pray.PrayLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesPrayLocalDataSource(prayDao: PrayDao): IPrayLocalDataSource {
        return PrayLocalDataSource(prayDao)
    }
}