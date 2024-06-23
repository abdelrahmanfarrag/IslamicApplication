package com.feature_quran.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ILoadQuranInitialDataUseCase
import com.islamic.domain.ResultState
import com.islamic.presentation.base.viewmodel.BaseViewModel
import com.islamic.presentation.base.viewmodel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
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
            is QuranContract.QuranEvents.PageOpened -> loadQuranInitialData()
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