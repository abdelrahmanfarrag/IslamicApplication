package com.islamic.app.viewmodel

import com.islamic.app.bottomnavigation.BottomNavigationItems
import com.islamic.app.navigation.Screens
import com.islamic.presentation.base.viewmodel.State

data class MainState(
    val bottomNavItems: ArrayList<BottomNavigationItems> = arrayListOf(),
    val mainRoute: Screens.BottomNavigation = BottomNavigationItems.Home.route,
    val shouldShow: Boolean = true
) : State