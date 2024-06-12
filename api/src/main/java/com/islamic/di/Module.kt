package com.islamic.di

import com.islamic.api.pray.PrayAPI
import com.islamic.di.qualifiers.PrayServer
import com.islamic.remotedatasource.networkcheck.CheckNetworkAvailability
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import com.islamic.remotedatasource.pray.PrayRemoteDataSource
import com.islamic.validateresponse.IValidateResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesPrayRemoteDataSource(
        @PrayServer prayAPI: PrayAPI,
        iCheckNetworkAvailability: CheckNetworkAvailability,
        iValidateResponse: IValidateResponse
    ): IPrayRemoteDataSource {
        return PrayRemoteDataSource(prayAPI, iCheckNetworkAvailability, iValidateResponse)
    }
}