package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class AdhanTimings(
    @SerializedName("Fajr")
    val fajr: String? = null,
    @SerializedName("Sunrise")
    val sunrise: String? = null,
    @SerializedName("Dhuhr")
    val dhuhr: String? = null,
    @SerializedName("Asr")
    val asr: String? = null,
    @SerializedName("Maghrib")
    val maghrib: String? = null,
    @SerializedName("Isha")
    val isha: String? = null
)