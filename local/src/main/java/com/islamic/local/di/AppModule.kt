package com.islamic.local.di

import android.content.Context
import androidx.room.Room
import com.islamic.local.database.IslamicAppDatabase
import com.islamic.local.database.dao.PrayDao
import com.islamic.local.database.dao.QuranDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {
        @Provides
        @Singleton
        fun providesIslamicDataBase(@ApplicationContext context: Context): IslamicAppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                IslamicAppDatabase::class.java,
                "islamic-app.db"
            ).build()
        }

        @Provides
        @Singleton
        fun providesPrayDao(islamicAppDatabase: IslamicAppDatabase): PrayDao {
            return islamicAppDatabase.prayDao
        }

        @Provides
        @Singleton
        fun providesQuranDao(islamicAppDatabase: IslamicAppDatabase): QuranDao {
            return islamicAppDatabase.quranDao
        }
    }
}