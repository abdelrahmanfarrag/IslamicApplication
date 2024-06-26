package com.feature_surrah.data.di

import android.view.View
import com.feature_surrah.data.repository.SurrahRepository
import com.feature_surrah.domain.repository.ISurrahRepository
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SurrahModule {

    @Provides
    @Singleton
    fun providesSurrahRepository(iQuranRemoteDataSource: IQuranRemoteDataSource):ISurrahRepository{
        return SurrahRepository(iQuranRemoteDataSource)
    }
}