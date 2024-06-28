package com.feature_surrah.presentation.exoplayer

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

class CreateExoPlayer @Inject constructor(
    private val mContext: Context
) : ICreateExoPlayer {
    override fun createExoPlayer(): ExoPlayer? {
        return ExoPlayer.Builder(mContext).build()
    }
}