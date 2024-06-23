package com.example.domain.di

import com.example.domain.repository.IQuranRepository
import com.example.domain.usecase.ILoadQuranInitialDataUseCase
import com.example.domain.usecase.LoadQuranInitialDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object QuranDomainModule {

    @Provides
    @ViewModelScoped
    fun providesLoadQuranInitialDataUseCase(iQuranRepository: IQuranRepository): ILoadQuranInitialDataUseCase {
        return LoadQuranInitialDataUseCase(iQuranRepository)
    }
}