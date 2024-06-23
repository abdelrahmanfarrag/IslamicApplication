package com.feature_quran.presentation.viewmodel

import com.example.domain.entities.QuranDTO
import com.islamic.domain.TextWrapper
import com.islamic.presentation.base.viewmodel.Event
import com.islamic.presentation.base.viewmodel.SingleUIEvent
import com.islamic.presentation.base.viewmodel.State

class QuranContract {
    data class QuranState(
        val isLoading: Boolean = false,
        val quranDto: QuranDTO? = null,
        val errorText: TextWrapper? = null
    ) : State

    sealed class QuranEvents : Event {
        data object PageOpened : QuranEvents()
    }

    sealed class QuranUIEvents : SingleUIEvent
}