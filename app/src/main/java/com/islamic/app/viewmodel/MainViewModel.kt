package com.islamic.app.viewmodel

import com.islamic.app.bottomnavigation.BottomNavigationItems
import com.islamic.presentation.base.viewmodel.BaseViewModel
import com.islamic.presentation.base.viewmodel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<MainState, MainEvents, MainSingleUIEvent>() {
    override fun createInitialState(): MainState {
        return MainState(
            arrayListOf(
                BottomNavigationItems.Home, BottomNavigationItems.Quran, BottomNavigationItems.Radio
            )
        )
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
        }

    }
}