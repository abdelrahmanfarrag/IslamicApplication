package com.islamic.app.navigation

import kotlinx.serialization.Serializable

object Screens {
    @Serializable
    object HomeScreen

    object BottomNavigation {
        @Serializable
        object Home

        @Serializable
        object Radio

        @Serializable
        object Quran
    }
}