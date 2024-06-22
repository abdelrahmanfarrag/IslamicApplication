package com.islamic.app.viewmodel

import com.islamic.app.navigation.Screens
import com.islamic.presentation.base.viewmodel.SingleUIEvent

sealed class MainSingleUIEvent : SingleUIEvent {
    data class OnRouteChange(val route: Screens.BottomNavigation) : MainSingleUIEvent()
}