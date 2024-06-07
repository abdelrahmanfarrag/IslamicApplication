package com.islamic.domain.mapper

import androidx.annotation.DrawableRes
import com.islamic.domain.TextWrapper
import com.islamic.core_domain.R
import java.time.LocalDateTime

sealed class SinglePray(
    val prayName: TextWrapper,
    val prayTime: LocalDateTime,
    val isNextPray: Boolean,
    val timeAfString: String?,
    @DrawableRes val icon: Int
) {
    data class Fajr(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(
            TextWrapper.ResourceText(R.string.fajr),
            time,
            isNext,
            hHmmTime,
            R.drawable.ic_night_moon
        )

    data class Shrouk(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(
            TextWrapper.ResourceText(R.string.shrouk),
            time,
            isNext,
            hHmmTime,
            R.drawable.ic_sunrise
        )

    data class Dhuhr(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(
            TextWrapper.ResourceText(R.string.dhuhr),
            time,
            isNext,
            hHmmTime,
            R.drawable.ic_sun
        )

    data class Asr(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(
            TextWrapper.ResourceText(R.string.asr),
            time,
            isNext,
            hHmmTime,
            R.drawable.ic_sun
        )

    data class Maghreb(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(
            TextWrapper.ResourceText(R.string.maghreb),
            time,
            isNext,
            hHmmTime,
            R.drawable.ic_sunset
        )

    data class Isha(val time: LocalDateTime, val isNext: Boolean, val hHmmTime: String? = null) :
        SinglePray(
            TextWrapper.ResourceText(R.string.isha),
            time,
            isNext,
            hHmmTime,
            R.drawable.ic_night_moon
        )

    data object Default :
        SinglePray(TextWrapper.StringText(""), LocalDateTime.now(), false, "", R.drawable.ic_moon)

}
