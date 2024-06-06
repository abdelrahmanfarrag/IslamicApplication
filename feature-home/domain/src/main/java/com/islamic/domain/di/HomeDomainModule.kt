package com.islamic.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.Clock

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeDomainModule {
    companion object {
        @Provides
        @ViewModelScoped
        fun providesClock(): Clock {
            return Clock.systemDefaultZone()
        }
    }
}