package com.islamic.domain.mapper


data class PrayDTO(
    val prays: ArrayList<SinglePray> = arrayListOf(),
    val skyState: SkyState ?= SkyState.BETWEEN_DHUHR_MAGHREB,
    val nextPray: SinglePray? = null,
    val day: String? = "",
    val monthName: String? = "",
    val dayOfMonth: String? = "",
    val hijriDate:String?=""
)