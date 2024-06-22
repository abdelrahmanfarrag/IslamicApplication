package com.islamic.presentation

import android.content.Context
import com.islamic.domain.TextWrapper
import com.islamic.domain.mapper.PrayDTO
import com.islamic.presentation.base.viewmodel.Event
import com.islamic.presentation.base.viewmodel.SingleUIEvent
import com.islamic.presentation.base.viewmodel.State

class HomeContract {

    data class UIState(
        val prayDTO: PrayDTO? = null,
        val textWrapper: TextWrapper? = null,
        val isLoading: Boolean = false
    ) : State

    sealed class HomeEvents : Event {
        data class CheckLocationPermission(
            val context: Context
        ) : HomeEvents()

        data class OnPermissionResultObtained(val permissions: Map<String, Boolean>) : HomeEvents()

        data object PageOpened : HomeEvents()
        data object LoadHomeContent : HomeEvents()
        data object LocationPermissionError : HomeEvents()
    }

    sealed class UIEvents : SingleUIEvent {
        data object RequestLocationPermission : UIEvents()
        data object LaunchRequestPermission : UIEvents()
       data object ShowPermissionsSnackBar : HomeContract.UIEvents()
    }
}