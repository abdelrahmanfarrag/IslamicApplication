package com.islamic.app.bottomnavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController:NavController) {
    val navBackStackEntry  by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation {


    }

}