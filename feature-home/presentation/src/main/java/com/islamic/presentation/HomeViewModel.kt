package com.islamic.presentation

import androidx.lifecycle.viewModelScope
import com.islamic.domain.ResultState
import com.islamic.domain.mapper.PrayDTO
import com.islamic.domain.usecase.home.ILoadPrayForHomeUseCase
import com.islamic.presentation.base.BaseViewModel
import com.islamic.presentation.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val iLoadPrayForHomeUseCase: ILoadPrayForHomeUseCase
) : BaseViewModel<HomeContract.UIState, HomeContract.HomeEvents, HomeContract.UIEvents>() {
    override fun createInitialState(): HomeContract.UIState {
        return HomeContract.UIState()
    }

    init {
        sendEvent(HomeContract.HomeEvents.PageOpened)
    }

    override fun onEvent(event: Event) {
        when (event) {
            HomeContract.HomeEvents.PageOpened -> callGetPray()
        }
    }

    private fun callGetPray() {
        viewModelScope.launch {
            toggleLoading(true)
            iLoadPrayForHomeUseCase.getPrayDTO().collect { state ->
                when (state) {
                    is ResultState.ResultSuccess<PrayDTO> -> {
                        setState {
                            copy(
                                isLoading = false,
                                prayDTO = state.result
                            )
                        }
                    }

                    is ResultState.ResultError -> {
                        setState {
                            copy(
                                isLoading = false,
                                textWrapper = state.textWrapper
                            )
                        }
                    }
                }
            }
        }
    }

    private fun toggleLoading(isLoading: Boolean) {
        setState {
            copy(isLoading = isLoading)
        }
    }
}