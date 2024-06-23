package com.islamic.local.di

import com.islamic.local.database.dao.PrayDao
import com.islamic.local.database.dao.QuranDao
import com.islamic.local.localdatasource.pray.IPrayLocalDataSource
import com.islamic.local.localdatasource.pray.PrayLocalDataSource
import com.islamic.local.localdatasource.quran.IQuranLocalDataSource
import com.islamic.local.localdatasource.quran.QuranLocalDataSource
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

    @Provides
    @Singleton
    fun providesQuranLocalDataSource(quranDao: QuranDao): IQuranLocalDataSource {
        return QuranLocalDataSource(quranDao)
    }
}