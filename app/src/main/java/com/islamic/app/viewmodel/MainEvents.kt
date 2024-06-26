package com.islamic.app.viewmodel

import com.islamic.app.navigation.Screens
import com.islamic.presentation.base.viewmodel.Event

sealed class MainEvents : Event {
    data class OnRouteUpdate(val route: Screens.BottomNavigation) : MainEvents()

    data class ShouldShowBottomNavigation(val currentRoute :String?):MainEvents()
}