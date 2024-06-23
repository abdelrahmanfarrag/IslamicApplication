package com.islamic.data.di

import com.islamic.data.repository.HomeRepository
import com.islamic.domain.repository.IHomeRepository
import com.islamic.local.localdatasource.pray.IPrayLocalDataSource
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesHomeRepository(
        iPrayRemoteDataSource: IPrayRemoteDataSource,
        iPrayLocalDataSource: IPrayLocalDataSource
    ): IHomeRepository {
        return HomeRepository(iPrayRemoteDataSource, iPrayLocalDataSource)
    }
}