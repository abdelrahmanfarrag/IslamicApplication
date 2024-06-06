package com.islamic.data.di

import com.islamic.data.repository.HomeRepository
import com.islamic.domain.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CoreModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsHomeRepository(homeRepository: HomeRepository): IHomeRepository

}