package com.islamic.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.islamic.api.pray.PrayAPI
import com.islamic.api.quran.QuranAPI
import com.islamic.di.qualifiers.PrayServer
import com.islamic.di.qualifiers.QuranServer
import com.islamic.endpoints.PrayEndPoints
import com.islamic.endpoints.QuranEndPoints
import com.islamic.remotedatasource.networkcheck.CheckNetworkAvailability
import com.islamic.remotedatasource.networkcheck.ICheckNetworkAvailability
import com.islamic.validateresponse.IValidateResponse
import com.islamic.validateresponse.ValidateResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
                // if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        //else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @QuranServer
    @Singleton
    fun providesQuranAPI(
        client: OkHttpClient,
        gson: Gson
    ): QuranAPI {
        return Retrofit.Builder()
            .baseUrl(QuranEndPoints.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @PrayServer
    @Singleton
    fun providesPrayAPI(
        client: OkHttpClient,
        gson: Gson
    ): PrayAPI {
        return Retrofit.Builder()
            .baseUrl(PrayEndPoints.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }

    @Provides
    @Singleton
    fun providesCheckNetworkAvailability(connectivityManager: ConnectivityManager): ICheckNetworkAvailability {
        return CheckNetworkAvailability(connectivityManager)
    }

    @Provides
    @Singleton
    fun providesValidateResponse(): IValidateResponse {
        return ValidateResponse()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }
}