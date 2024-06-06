package com.islamic.domain.di

import com.islamic.domain.repository.IHomeRepository
import com.islamic.domain.usecase.home.ILoadPrayForHomeUseCase
import com.islamic.domain.usecase.home.LoadPrayForHomeUseCase
import com.islamic.domain.usecase.date.IGetCurrentDateUseCase
import com.islamic.domain.usecase.location.IGetUserLocation
import com.islamic.domain.usecase.praytimes.GetPrayTimeUseCase
import com.islamic.domain.usecase.praytimes.IGetPrayTimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.Clock

@Module(includes = [CoreDomainModule::class])
@InstallIn(ViewModelComponent::class)
class HomeDomainModule {

    @Provides
    @ViewModelScoped
    fun provideGetPrayTimesUseCase(iHomeRepository: IHomeRepository): IGetPrayTimeUseCase {
        return GetPrayTimeUseCase(iHomeRepository)
    }

    @Provides
    @ViewModelScoped
    fun providesClock(): Clock {
        return Clock.systemDefaultZone()
    }

    @Provides
    @ViewModelScoped
    fun bindsLoadPrayForHomeUseCase(
        iGetPrayTimesUseCase: IGetPrayTimeUseCase,
        iGetUserLocationUseCase: IGetUserLocation,
        iGetCurrentDateUseCase: IGetCurrentDateUseCase,
        clock: Clock
    ): ILoadPrayForHomeUseCase {
        return LoadPrayForHomeUseCase(
            iGetPrayTimesUseCase,
            iGetUserLocationUseCase,
            iGetCurrentDateUseCase,
            clock
        )
    }
}