package com.islamic.domain.di

import com.islamic.domain.usecase.praytimes.GetPrayTimeUseCase
import com.islamic.domain.usecase.praytimes.IGetPrayTimeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CoreDomainModule {

    companion object {

    }

    @Binds
    @ViewModelScoped
    abstract fun bindsGetPrayTimesUseCase(getPrayTimeUseCase: GetPrayTimeUseCase): IGetPrayTimeUseCase
}