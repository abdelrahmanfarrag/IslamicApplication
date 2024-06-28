package com.feature_surrah.presentation.viewmodel

import com.feature_surrah.domain.models.Surrah
import com.islamic.domain.TextWrapper
import com.islamic.presentation.base.viewmodel.Event
import com.islamic.presentation.base.viewmodel.SingleUIEvent
import com.islamic.presentation.base.viewmodel.State

class SurrahContract {
    data class UIState(
        val surrahNumber: Int = 0,
        val audioId: String = "",
        val tafsirId: String = "",
        val surrah: Surrah? = Surrah(),
        val isLoading: Boolean = false,
        val errorText: TextWrapper? = null
    ) : State

    sealed class SurrahEvents : Event {
        data object PageOpened : SurrahEvents()
        data object GetSurrah : SurrahEvents()
    }

    sealed class SurrahUIEvents : SingleUIEvent
}