package com.feature_surrah.presentation.viewmodel

import androidx.media3.exoplayer.ExoPlayer
import com.feature_surrah.domain.models.Surrah
import com.islamic.domain.TextWrapper
import com.islamic.presentation.base.viewmodel.Event
import com.islamic.presentation.base.viewmodel.SingleUIEvent
import com.islamic.presentation.base.viewmodel.State

class SurrahContract {
    data class UIState(
        val surrahNumber: Int = 0,
        val audioId: String = "",
        val tafsirId: String = "",
        val surrah: Surrah? = Surrah(),
        val isLoading: Boolean = false,
        val errorText: TextWrapper? = null,
        val powerRatio: List<Float> = arrayListOf(),
        val exoPlayer: ExoPlayer? = null,
        val playingAyahNumber: Int? = -1
    ) : State

    sealed class SurrahEvents : Event {
        data object PageOpened : SurrahEvents()
        data object GetSurrah : SurrahEvents()

        data class OnPlayClick(val mediaURL: String?, val ayahNumber: Int?) : SurrahEvents()
    }

    sealed class SurrahUIEvents : SingleUIEvent
}