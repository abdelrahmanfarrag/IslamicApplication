package com.example.data.di

import com.example.data.repository.QuranRepository
import com.example.domain.repository.IQuranRepository
import com.google.gson.Gson
import com.islamic.entities.quran.Quran
import com.islamic.local.localdatasource.quran.IQuranLocalDataSource
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuranDataModule {

    @Provides
    @Singleton
    fun providesQuranRepository(
        iQuranRemoteDataSource: IQuranRemoteDataSource,
        iQuranLocalDataSource: IQuranLocalDataSource,
        gson: Gson
    ): IQuranRepository {
        return QuranRepository(iQuranRemoteDataSource, iQuranLocalDataSource,gson)
    }
}