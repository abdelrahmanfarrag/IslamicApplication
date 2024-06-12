package com.islamic.services.di

import android.app.AlarmManager
import android.content.Context
import com.islamic.services.alarmpraytime.AlarmPrayTime
import com.islamic.services.alarmpraytime.IAlarmPrayTime
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun providesAlarmManager(@ApplicationContext context: Context): AlarmManager {
        return context.getSystemService(AlarmManager::class.java)
    }

    @Provides
    @Singleton
    fun providesAlarmPrayTime(
        alarmManager: AlarmManager,
        @ApplicationContext context: Context
    ): IAlarmPrayTime {
        return AlarmPrayTime(alarmManager, context)
    }
}