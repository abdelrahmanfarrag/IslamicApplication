package com.islamic.app.navigation

import kotlinx.serialization.Serializable

object Screens {

    @Serializable
    sealed class BottomNavigation {
        @Serializable
        data object HomeScreen : BottomNavigation()

        @Serializable
        data object RadioScreen : BottomNavigation()

        @Serializable
        data object QuranScreen : BottomNavigation()
    }
}