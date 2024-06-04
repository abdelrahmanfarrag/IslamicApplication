package com.islamic.entities.pray

import com.squareup.moshi.Json

data class Hijri(
    @field:Json(name = "month")
    val month:HijriMonth?=null,
    @field:Json(name = "weekday")
    val weekday:WeekDay?=null
)
