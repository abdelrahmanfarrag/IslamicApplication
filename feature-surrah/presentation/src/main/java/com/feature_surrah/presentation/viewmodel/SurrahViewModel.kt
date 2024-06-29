package com.feature_surrah.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.feature_surrah.domain.models.Ayah
import com.feature_surrah.domain.usecase.IGetSurrahUseCase
import com.feature_surrah.presentation.exoplayer.ICreateExoPlayer
import com.islamic.domain.ResultState
import com.islamic.domain.extension.replaceEnglishNumberWithArabic
import com.islamic.presentation.base.viewmodel.BaseViewModel
import com.islamic.presentation.base.viewmodel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class SurrahViewModel @Inject constructor(
    private val iGetSurrahUseCase: IGetSurrahUseCase,
    private val mSavedStateHandle: SavedStateHandle,
    private val iCreateExoPlayer: ICreateExoPlayer
) : BaseViewModel<SurrahContract.UIState, SurrahContract.SurrahEvents, SurrahContract.SurrahUIEvents>() {
    init {
        sendEvent(SurrahContract.SurrahEvents.PageOpened)
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(100)
                setState {
                    copy(powerRatio = (0..50).map {
                        Random.nextFloat() / 4
                    })
                }
            }
        }
    }

    override fun createInitialState(): SurrahContract.UIState {
        return SurrahContract.UIState()
    }

    override fun onEvent(event: Event) {
        when (event) {
            is SurrahContract.SurrahEvents.OnPlayClick -> onPlaySurrahClick(
                event.mediaURL,
                event.ayahNumber
            )

            is SurrahContract.SurrahEvents.OnShareClick -> onShareSurrahClick(
                event.context,
                event.ayah,
                event.tafsir,
                event.number
            )

            SurrahContract.SurrahEvents.PageOpened -> onPageOpened()
            SurrahContract.SurrahEvents.GetSurrah -> getSurrah()
        }
    }

    private fun onShareSurrahClick(
        context: Context,
        ayah: String?,
        tafsir: String?,
        number: String?
    ) {
        val message = context.getString(
            com.islamic.core_domain.R.string.share_tafsir_text,
            ayah,
            number.replaceEnglishNumberWithArabic(),
            currentState.surrah?.surrahName,
            currentState.tafsirName,
            tafsir
        )
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, message)
        context.startActivity(
            Intent.createChooser(
                share,
                "Test"
            )
        )

    }


    private fun updatePlayingList(
        ayahNumber: Int?,
        ayahPlayingState: Ayah.AyahPlayingState
    ): ArrayList<Ayah> {
        val updateItems = currentState.surrah?.ayahs?.map {
            return@map if (it.ayahNumber == ayahNumber)
                it.copy(ayahPlayingAudioState = ayahPlayingState)
            else
                it.copy(ayahPlayingAudioState = Ayah.AyahPlayingState.IDLE)
        } as ArrayList
        return updateItems
    }

    private fun onPlaySurrahClick(mediaURL: String?, ayahNumber: Int?) {
        mediaURL?.let {
            val mediaSource = MediaItem.fromUri(it)
            currentState.exoPlayer?.setMediaItem(mediaSource)
            currentState.exoPlayer?.prepare()
            currentState.exoPlayer?.play()
            setState {
                copy(
                    playingAyahNumber = ayahNumber
                )
            }
        }

    }

    private fun initPlayer() {
        currentState.exoPlayer?.let {
            it.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    when (playbackState) {
                        Player.STATE_ENDED -> {
                            setState {
                                copy(
                                    surrah = currentState.surrah?.copy(
                                        ayahs = updatePlayingList(
                                            currentState.playingAyahNumber,
                                            Ayah.AyahPlayingState.ENDED
                                        )
                                    ),
                                    playingAyahNumber = null
                                )
                            }
                        }

                        Player.STATE_BUFFERING -> {
                            setState {
                                copy(
                                    surrah = currentState.surrah?.copy(
                                        ayahs = updatePlayingList(
                                            currentState.playingAyahNumber,
                                            Ayah.AyahPlayingState.BUFFERING
                                        )
                                    ),
                                )
                            }
                        }

                        Player.STATE_READY -> {
                            setState {
                                copy(
                                    surrah = currentState.surrah?.copy(
                                        ayahs = updatePlayingList(
                                            currentState.playingAyahNumber,
                                            Ayah.AyahPlayingState.PLAYING
                                        )
                                    ),
                                )
                            }
                        }

                        Player.STATE_IDLE -> {
                            setState {
                                copy(
                                    surrah = currentState.surrah?.copy(
                                        ayahs = updatePlayingList(
                                            currentState.playingAyahNumber,
                                            Ayah.AyahPlayingState.IDLE
                                        )
                                    ),
                                )
                            }
                        }
                    }
                }
            })
        }
    }

    private fun getSurrah() {
        viewModelScope.launch {

            iGetSurrahUseCase.getSurrah(
                currentState.surrahNumber, arrayListOf(
                    currentState.audioId, currentState.tafsirId
                )
            ).collect { surrahResult ->

                if (surrahResult is ResultState.ResultSuccess)
                    setState {
                        copy(
                            surrah = surrahResult.result,
                            isLoading = false,
                        )
                    }
                else if (surrahResult is ResultState.ResultError)
                    setState {
                        copy(
                            errorText = surrahResult.textWrapper,
                            isLoading = false
                        )
                    }
            }
        }
    }

    private fun onPageOpened() {
        val surrahNumber = mSavedStateHandle.get<Int>("number") ?: -1
        val audioId = mSavedStateHandle.get<String>("audioId") ?: ""
        val tafsirId = mSavedStateHandle.get<String>("tafsirId") ?: ""
        val tafsirName = mSavedStateHandle.get<String>("tafsirName") ?: ""
        setState {
            copy(
                surrahNumber = surrahNumber,
                audioId = audioId,
                tafsirId = tafsirId,
                isLoading = true,
                exoPlayer = iCreateExoPlayer.createExoPlayer(),
                tafsirName = tafsirName
            )
        }
        initPlayer()
        sendEvent(SurrahContract.SurrahEvents.GetSurrah)
    }


}