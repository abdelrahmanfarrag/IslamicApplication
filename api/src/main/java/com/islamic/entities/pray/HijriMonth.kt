package com.islamic.entities.pray

import com.squareup.moshi.Json

data class HijriMonth(
    @field:Json(name = "number")
    val number:Int?=null,
    @field:Json(name = "ar")
    val arabicName:String?=null
)
