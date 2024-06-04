package com.islamic.entities.pray

import com.squareup.moshi.Json

data class WeekDay(
    @field:Json(name = "ar")
    val dayName: String? = null
)
