package com.islamic.di

import android.content.Context
import android.net.ConnectivityManager
import com.islamic.api.quran.QuranAPI
import com.islamic.di.qualifiers.QuranServer
import com.islamic.endpoints.QuranEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun providesQuranAPI(client: OkHttpClient): QuranAPI {
        return Retrofit.Builder()
            .baseUrl(QuranEndPoints.SERVER_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesConnectivityManager(@ApplicationContext context: Context):ConnectivityManager{
        return context.getSystemService(ConnectivityManager::class.java)
    }
}