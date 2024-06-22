package com.islamic.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.mapper.PrayDTO
import com.islamic.domain.usecase.home.ILoadPrayForHomeUseCase
import com.islamic.presentation.base.viewmodel.BaseViewModel
import com.islamic.presentation.base.viewmodel.Event
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
            is HomeContract.HomeEvents.CheckLocationPermission -> checkLocationPermission(event.context)
            is HomeContract.HomeEvents.OnPermissionResultObtained -> onPermissionsObtained(event.permissions)
            HomeContract.HomeEvents.PageOpened -> sendSingleUIEvent {
                HomeContract.UIEvents.RequestLocationPermission
            }

            HomeContract.HomeEvents.LoadHomeContent -> callGetPray()
            HomeContract.HomeEvents.LocationPermissionError -> setErrorOnLocationNotGranted()
        }
    }

    private fun onPermissionsObtained(permissions: Map<String, Boolean>) {
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) sendEvent(
            HomeContract.HomeEvents.LoadHomeContent
        )
        else sendSingleUIEvent {
            HomeContract.UIEvents.ShowPermissionsSnackBar
        }
    }

    private fun setErrorOnLocationNotGranted() {
        setState {
            copy(
                textWrapper = TextWrapper.ResourceText(com.islamic.core_domain.R.string.permission_not_granted)
            )
        }
    }

    private fun checkLocationPermission(context: Context) {
        when {
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> sendEvent(HomeContract.HomeEvents.LoadHomeContent)

            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity, Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                setErrorOnLocationNotGranted()
                sendSingleUIEvent {
                    HomeContract.UIEvents.ShowPermissionsSnackBar
                }
            }

            else -> {
                setErrorOnLocationNotGranted()

                sendSingleUIEvent {
                    HomeContract.UIEvents.LaunchRequestPermission
                }
            }
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
                                isLoading = false, prayDTO = state.result
                            )
                        }
                    }

                    is ResultState.ResultError -> {
                        setState {
                            copy(
                                isLoading = false, textWrapper = state.textWrapper
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