package com.feature_surrah.presentation.viewmodel

import androidx.media3.exoplayer.ExoPlayer
import com.feature_surrah.presentation.exoplayer.ICreateExoPlayer

class FakeExoPlayer : ICreateExoPlayer {
    override fun createExoPlayer(): ExoPlayer? {
        return null
    }
}