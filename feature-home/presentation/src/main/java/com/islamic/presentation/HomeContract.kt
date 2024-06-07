package com.islamic.presentation

import com.islamic.domain.TextWrapper
import com.islamic.domain.mapper.PrayDTO
import com.islamic.presentation.base.Event
import com.islamic.presentation.base.SingleUIEvent
import com.islamic.presentation.base.State

class HomeContract {

    data class UIState(
        val prayDTO: PrayDTO? = null,
        val textWrapper: TextWrapper? = null,
        val isLoading: Boolean = false
    ) : State

    sealed class HomeEvents : Event {
        data object PageOpened : HomeEvents()
    }

    sealed class UIEvents : SingleUIEvent
}