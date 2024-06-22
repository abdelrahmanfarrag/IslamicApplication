package com.islamic.domain.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.islamic.domain.usecase.hijridate.GetHijriDate
import com.islamic.domain.usecase.hijridate.IGetHijriDate
import com.islamic.domain.usecase.location.GetUserLocation
import com.islamic.domain.usecase.location.IGetUserLocation
import com.islamic.domain.usecase.locationpermission.CheckLocationPermissionUseCase
import com.islamic.domain.usecase.locationpermission.ICheckLocationPermissionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import java.time.Clock
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFusedLocationProvider(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun providesGeoCoder(
        @ApplicationContext context: Context
    ): Geocoder {
        return Geocoder(context, Locale.getDefault())
    }

    @Provides
    @Singleton
    fun providesGetUserLocation(
        fusedLocationPerClient: FusedLocationProviderClient,
        geocoder: Geocoder,
        iCheckLocationPermissionUseCase: ICheckLocationPermissionUseCase
    ): IGetUserLocation {
        return GetUserLocation(fusedLocationPerClient, geocoder, iCheckLocationPermissionUseCase)
    }

    @Provides
    @Singleton
    fun providesGetHijriDate(): IGetHijriDate {
        return GetHijriDate()
    }

    @Provides
    @Singleton
    fun providesClock(): Clock {
        return Clock.systemDefaultZone()
    }

    @Provides
    @Singleton
    fun providesCheckLocationPermissionUseCase(@ApplicationContext context: Context): ICheckLocationPermissionUseCase {
        return CheckLocationPermissionUseCase(context)
    }
}