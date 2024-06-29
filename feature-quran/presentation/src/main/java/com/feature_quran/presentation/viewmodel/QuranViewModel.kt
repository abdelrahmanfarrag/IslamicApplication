package com.feature_quran.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ILoadQuranInitialDataUseCase
import com.islamic.domain.ResultState
import com.islamic.presentation.base.viewmodel.BaseViewModel
import com.islamic.presentation.base.viewmodel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val iLoadQuranInitialDataUseCase: ILoadQuranInitialDataUseCase
) : BaseViewModel<QuranContract.QuranState, QuranContract.QuranEvents, QuranContract.QuranUIEvents>() {

    init {
        sendEvent(QuranContract.QuranEvents.PageOpened)
    }

    override fun createInitialState(): QuranContract.QuranState {
        return QuranContract.QuranState()
    }

    override fun onEvent(event: Event) {
        when (event) {
            is QuranContract.QuranEvents.OnSurrahChose -> onSurrahChose(
                event.surrahNumber,
                event.name
            )

            is QuranContract.QuranEvents.OnTafsirSelected -> onTafsirSelect(
                event.tafsirId,
                event.tafsirName
            )

            is QuranContract.QuranEvents.OnAudioSelected -> onAudioSelected(event.audioId)
            is QuranContract.QuranEvents.ChangeBottomSheetState -> setState {
                copy(
                    shouldShowModalBottomSheet = event.isVisible
                )
            }

            QuranContract.QuranEvents.PageOpened -> loadQuranInitialData()
            QuranContract.QuranEvents.OnContinueClick -> {
                sendSingleUIEvent {
                    QuranContract.QuranUIEvents.NavigateToSurrahPage(
                        audioId = currentState.sheikhId,
                        tafsirId = currentState.tafsirId,
                        number = currentState.surrahNumber,
                        tafsirName = currentState.tafsirName
                    )
                }
            }

        }
    }

    private fun onAudioSelected(audioId: String?) {
        audioId?.let { id ->
            val updateTafsir = currentState.quranDto?.quranSheikhAudios?.map {
                return@map it.copy(
                    isSelected = id == it.id
                )
            }
            setState {
                copy(
                    quranDto = currentState.quranDto?.copy(
                        quranSheikhAudios = updateTafsir
                    ),
                    sheikhId = id
                )
            }
        }

    }

    private fun onTafsirSelect(tafsirId: String?, tafsirName: String?) {
        tafsirId?.let { id ->
            val updateTafsir = currentState.quranDto?.quranTafsir?.map {
                return@map it.copy(
                    isSelected = id == it.id
                )

            }
            setState {
                copy(
                    quranDto = currentState.quranDto?.copy(
                        quranTafsir = updateTafsir
                    ),
                    tafsirId = tafsirId,
                    tafsirName = tafsirName
                )
            }
        }

    }

    private fun onSurrahChose(surrahNumber: Int?, name: String?) {
        if (surrahNumber != null) {
            setState {
                copy(
                    surrahNumber = surrahNumber,
                    shouldShowModalBottomSheet = true,
                    name = name
                )
            }

        }
    }

    private fun loadQuranInitialData() {
        setState {
            copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            iLoadQuranInitialDataUseCase().collect { result ->
                if (result is ResultState.ResultSuccess) {
                    setState {
                        copy(
                            isLoading = false,
                            quranDto = result.result
                        )
                    }
                } else if (result is ResultState.ResultError) {
                    setState {
                        copy(
                            isLoading = false,
                            errorText = result.textWrapper
                        )
                    }
                }
            }
        }
    }
}