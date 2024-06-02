package com.islamic.entities.quran

import com.squareup.moshi.Json

data class Quran(
    @field:Json(name = "number")
    val number: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "revelationType")
    val revealType: String?,
    @field:Json(name = "numberOfAyahs")
    val countOfAyahs: Int?,
    @field:Json(name = "ayahs")
    val ayahs: ArrayList<Ayah>?,
    @field:Json(name = "edition")
    val edition: Edition?

)
