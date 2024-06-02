package com.islamic.entities.quran

import com.squareup.moshi.Json

data class Ayah(
    @field:Json(name = "number")
    val number: Int?,
    @field:Json(name = "text")
    val text: String?,
    @field:Json(name = "juz")
    val juz: String?,
    @field:Json(name = "audio")
    val audio: String?,
)