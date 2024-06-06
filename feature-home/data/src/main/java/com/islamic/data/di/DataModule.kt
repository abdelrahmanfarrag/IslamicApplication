package com.islamic.data.di

import com.islamic.data.repository.HomeRepository
import com.islamic.domain.repository.IHomeRepository
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    @ViewModelScoped
    fun providesHomeRepository(iPrayRemoteDataSource: IPrayRemoteDataSource):IHomeRepository{
        return HomeRepository(iPrayRemoteDataSource)
    }
}