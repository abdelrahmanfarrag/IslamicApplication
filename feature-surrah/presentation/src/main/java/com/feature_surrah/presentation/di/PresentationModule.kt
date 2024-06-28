package com.feature_surrah.presentation.di

import android.content.Context
import com.feature_surrah.presentation.exoplayer.CreateExoPlayer
import com.feature_surrah.presentation.exoplayer.ICreateExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    @ViewModelScoped
    fun providesExoPlayer(@ApplicationContext context: Context): ICreateExoPlayer {
        return CreateExoPlayer(context)
    }
}