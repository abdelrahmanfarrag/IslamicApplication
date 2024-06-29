package com.islamic.app.viewmodel

import com.islamic.app.bottomnavigation.BottomNavigationItems
import com.islamic.app.navigation.Screens
import com.islamic.presentation.base.viewmodel.State
import java.util.Locale

data class MainState(
    val bottomNavItems: ArrayList<BottomNavigationItems> = arrayListOf(),
    val mainRoute: Screens.BottomNavigation = BottomNavigationItems.Home.route,
    val shouldShow: Boolean = true,
    val locale: String = Locale.getDefault().language
) : State