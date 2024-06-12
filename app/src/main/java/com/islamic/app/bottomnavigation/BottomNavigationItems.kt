package com.islamic.app.bottomnavigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.islamic.app.R
import com.islamic.app.navigation.Screens
import kotlin.reflect.KClass

sealed class BottomNavigationItems(
    val route: KClass<Any>,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    data object Home : BottomNavigationItems(Screens.BottomNavigation.Home,Icons.Default.Home, com.islamic.core_domain.R.string.home)
}