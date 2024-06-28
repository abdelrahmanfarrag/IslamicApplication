package com.feature_surrah.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.feature_surrah.domain.usecase.IGetSurrahUseCase
import com.islamic.domain.ResultState
import com.islamic.presentation.base.viewmodel.BaseViewModel
import com.islamic.presentation.base.viewmodel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurrahViewModel @Inject constructor(
    private val iGetSurrahUseCase: IGetSurrahUseCase,
    private val mSavedStateHandle: SavedStateHandle
) : BaseViewModel<SurrahContract.UIState, SurrahContract.SurrahEvents, SurrahContract.SurrahUIEvents>() {
    init {
        sendEvent(SurrahContract.SurrahEvents.PageOpened)
    }

    override fun createInitialState(): SurrahContract.UIState {
        return SurrahContract.UIState()
    }

    override fun onEvent(event: Event) {
        when (event) {
            SurrahContract.SurrahEvents.PageOpened -> onPageOpened()
            SurrahContract.SurrahEvents.GetSurrah -> getSurrah()
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
                            isLoading = false
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
        setState {
            copy(
                surrahNumber = surrahNumber,
                audioId = audioId,
                tafsirId = tafsirId,
                isLoading = true
            )
        }
        sendEvent(SurrahContract.SurrahEvents.GetSurrah)
    }


}