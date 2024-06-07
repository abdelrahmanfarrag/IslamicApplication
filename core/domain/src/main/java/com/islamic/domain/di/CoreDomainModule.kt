package com.islamic.domain.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.islamic.domain.usecase.date.GetCurrentDateUseCase
import com.islamic.domain.usecase.date.IGetCurrentDateUseCase
import com.islamic.domain.usecase.hijridate.GetHijriDate
import com.islamic.domain.usecase.hijridate.IGetHijriDate
import com.islamic.domain.usecase.location.GetUserLocation
import com.islamic.domain.usecase.location.IGetUserLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.Clock
import java.time.chrono.HijrahDate

@Module
@InstallIn(ViewModelComponent::class)
class CoreDomainModule {


    @Provides
    @ViewModelScoped
    fun providesGetCurrentDateUseCase(clock: Clock): IGetCurrentDateUseCase {
        return GetCurrentDateUseCase(clock)
    }

    @Provides
    @ViewModelScoped
    fun providesGetUserLocation(
        fusedLocationPerClient: FusedLocationProviderClient,
        geocoder: Geocoder,
        @ApplicationContext context: Context
    ): IGetUserLocation {
        return GetUserLocation(fusedLocationPerClient, geocoder, context)
    }

    @Provides
    @ViewModelScoped
    fun providesGetHijriDate(): IGetHijriDate {
        return GetHijriDate()
    }


}