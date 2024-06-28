package com.feature_surrah.domain.di

import com.feature_surrah.domain.repository.ISurrahRepository
import com.feature_surrah.domain.usecase.GetSurrahUseCase
import com.feature_surrah.domain.usecase.IGetSurrahUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SurrahDomainModule {

    @Provides
    @ViewModelScoped
    fun providesGetSurrahUseCase(iSurrahRepository: ISurrahRepository): IGetSurrahUseCase {
        return GetSurrahUseCase(iSurrahRepository)
    }
}