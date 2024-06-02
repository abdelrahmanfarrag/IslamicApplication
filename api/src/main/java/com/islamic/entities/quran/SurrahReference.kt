package com.islamic.entities.quran

import com.squareup.moshi.Json

data class SurrahReference(
    @field:Json(name = "number")
    val number:Int?,
    @field:Json(name = "name")
    val name:String?,
    @field:Json(name = "numberOfAyahs")
    val countOfAyahs:String?
)