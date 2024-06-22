package com.islamic.app.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.islamic.app.R
import com.islamic.app.navigation.Screens

sealed class BottomNavigationItems(
    val route: Screens.BottomNavigation,
    @DrawableRes val res: Int,
    @StringRes val label: Int
) {
    data object Home : BottomNavigationItems(
        Screens.BottomNavigation.HomeScreen, R.drawable.ic_home,
        com.islamic.core_domain.R.string.home
    )

    data object Quran : BottomNavigationItems(
        Screens.BottomNavigation.QuranScreen,
        R.drawable.ic_quran,
        com.islamic.core_domain.R.string.quran
    )

    data object Radio : BottomNavigationItems(
        Screens.BottomNavigation.RadioScreen,
        R.drawable.ic_radio,
        com.islamic.core_domain.R.string.radio
    )
}