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
        val errorText: TextWrapper? = null,
        val surrahNumber: Int? = null,
        val name:String?=null,
        val shouldShowModalBottomSheet: Boolean = false,
        val tafsirId:String?=null,
        val sheikhId:String?=null,
        val tafsirName:String?=null
    ) : State

    sealed class QuranEvents : Event {

        data class ChangeBottomSheetState(val isVisible: Boolean) : QuranEvents()
        data object PageOpened : QuranEvents()
        data class OnSurrahChose(val surrahNumber: Int?,val name:String?) : QuranEvents()

        data object OnContinueClick : QuranEvents()
        data class OnAudioSelected(val audioId: String?) : QuranEvents()
        data class OnTafsirSelected(val tafsirId: String?,val tafsirName:String?) : QuranEvents()
    }

    sealed class QuranUIEvents : SingleUIEvent {
        data class NavigateToSurrahPage(
            val tafsirId: String?,
            val audioId: String?,
            val number:Int?,
            val tafsirName:String?
        ) : QuranUIEvents()
    }
}