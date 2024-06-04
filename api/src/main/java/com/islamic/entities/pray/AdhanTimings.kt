package com.islamic.entities.pray

import com.squareup.moshi.Json

data class AdhanTimings(
    @field:Json(name = "Fajr")
    val fajr: String? = null,
    @field:Json(name = "Sunrise")
    val sunrise: String? = null,
    @field:Json(name = "Dhuhr")
    val dhuhr: String? = null,
    @field:Json(name = "Asr")
    val asr: String? = null,
    @field:Json(name = "Maghrib")
    val maghrib: String? = null,
    @field:Json(name = "Isha")
    val isha: String? = null
)