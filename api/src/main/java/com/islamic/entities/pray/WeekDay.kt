package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class WeekDay(
    @SerializedName("ar")
    val dayName: String? = null
)
