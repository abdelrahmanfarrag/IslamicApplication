package com.islamic.app.viewmodel

import com.islamic.app.bottomnavigation.BottomNavigationItems
import com.islamic.local.languagemanager.IChangeLanguageManager
import com.islamic.presentation.base.viewmodel.BaseViewModel
import com.islamic.presentation.base.viewmodel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val iChangeLanguage: IChangeLanguageManager
) : BaseViewModel<MainState, MainEvents, MainSingleUIEvent>() {

    override fun createInitialState(): MainState {
        return MainState(
            arrayListOf(
                BottomNavigationItems.Home, BottomNavigationItems.Quran, BottomNavigationItems.Radio
            ),
            mainRoute = BottomNavigationItems.Home.route)
    }

    override fun onEvent(event: Event) {
        when (event) {
            is MainEvents.OnRouteUpdate -> {
                if (event.route != currentState.mainRoute) {
                    setState {
                        copy(
                            mainRoute = event.route
                        )
                    }
                    sendSingleUIEvent {
                        MainSingleUIEvent.OnRouteChange(event.route)
                    }
                }
            }

            is MainEvents.ShouldShowBottomNavigation -> {
                event.currentRoute?.let {
                    val shouldShowBottomNav = it == currentState.mainRoute.javaClass.canonicalName
                    setState {
                        copy(
                            shouldShow = shouldShowBottomNav
                        )
                    }
                }


            }
        }

    }
}