package com.islamic.local.di

import android.app.LocaleManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.islamic.local.database.IslamicAppDatabase
import com.islamic.local.database.dao.PrayDao
import com.islamic.local.database.dao.QuranDao
import com.islamic.local.languagemanager.ChangeLanguageManager
import com.islamic.local.languagemanager.IChangeLanguageManager
import com.islamic.local.preferences.IPreferencesRepository
import com.islamic.local.preferences.PreferencesRepository
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
            ).allowMainThreadQueries().fallbackToDestructiveMigration()
                .build()
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

        @Provides
        @Singleton
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        fun providePreferencesRepository(sharedPreferences: SharedPreferences): IPreferencesRepository {
            return PreferencesRepository(sharedPreferences)
        }

        @Provides
        @Singleton
        fun providesLocaleManagerService(@ApplicationContext context: Context): LocaleManager? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java)
            } else {
                null
            }
        }

        @Provides
        @Singleton
        fun providesChangeLanguageManager(localeManager: LocaleManager?): IChangeLanguageManager {
            return ChangeLanguageManager(localeManager)
        }
    }
}