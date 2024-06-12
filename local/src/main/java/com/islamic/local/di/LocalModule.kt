package com.islamic.local.di

import com.islamic.local.database.PrayDao
import com.islamic.local.localdatasource.IPrayLocalDataSource
import com.islamic.local.localdatasource.PrayLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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