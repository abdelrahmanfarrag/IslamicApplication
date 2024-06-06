package com.islamic.domain.mapper

import com.islamic.domain.TextWrapper
import com.islamic.core_domain.R
import java.time.LocalDateTime

sealed class SinglePray(
    val prayName: TextWrapper,
    val prayTime: LocalDateTime,
    val isNextPray: Boolean,
    val timeAfString: String?
) {
    data class Fajr(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(TextWrapper.ResourceText(R.string.fajr), time, isNext, hHmmTime)

    data class Shrouk(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(TextWrapper.ResourceText(R.string.shrouk), time, isNext, hHmmTime)

    data class Dhuhr(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(TextWrapper.ResourceText(R.string.dhuhr), time, isNext, hHmmTime)

    data class Asr(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(TextWrapper.ResourceText(R.string.asr), time, isNext, hHmmTime)

    data class Maghreb(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(TextWrapper.ResourceText(R.string.maghreb), time, isNext, hHmmTime)

    data class Isha(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(TextWrapper.ResourceText(R.string.isha), time, isNext, hHmmTime)

}
