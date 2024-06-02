package com.islamic.entities.quran

import com.squareup.moshi.Json

data class QuranEditionType(
    @field:Json(name = "identifier")
    val id: String?,
    @field:Json(name = "language")
    val lang: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "englishName")
    val englishName: String?,
    @field:Json(name = "format")
    val format: String?,
    @field:Json(name = "type")
    val type: String?,
)
