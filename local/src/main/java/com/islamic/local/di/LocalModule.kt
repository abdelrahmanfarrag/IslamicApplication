package com.islamic.local.di

import com.islamic.local.database.PrayDao
import com.islamic.local.localdatasource.IPrayLocalDataSource
import com.islamic.local.localdatasource.PrayLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class LocalModule {

    @Provides
    @ViewModelScoped
    fun providesPrayLocalDataSource(prayDao: PrayDao): IPrayLocalDataSource {
        return PrayLocalDataSource(prayDao)
    }
}