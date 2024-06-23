package com.islamic.di

import com.islamic.api.pray.PrayAPI
import com.islamic.api.quran.QuranAPI
import com.islamic.di.qualifiers.PrayServer
import com.islamic.di.qualifiers.QuranServer
import com.islamic.entities.quran.Quran
import com.islamic.remotedatasource.networkcheck.CheckNetworkAvailability
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import com.islamic.remotedatasource.pray.PrayRemoteDataSource
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import com.islamic.remotedatasource.quran.QuranRemoteDataSource
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

    @Provides
    @Singleton
    fun providesQuranRemoteDataSource(
        @QuranServer quran: QuranAPI,
        iCheckNetworkAvailability: CheckNetworkAvailability,
        iValidateResponse: IValidateResponse

    ): IQuranRemoteDataSource {
        return QuranRemoteDataSource(iCheckNetworkAvailability, iValidateResponse, quran)
    }
}