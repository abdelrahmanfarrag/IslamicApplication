package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class Hijri(
    @SerializedName("month")
    val month: HijriMonth? = null,
    @SerializedName("weekday")
    val weekday: WeekDay? = null
)
