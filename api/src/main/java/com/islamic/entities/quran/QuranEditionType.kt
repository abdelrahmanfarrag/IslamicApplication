package com.islamic.entities.quran

import com.squareup.moshi.Json

data class QuranEditionType(
    @field:Json(name = "identifier")
    val id: String?=null,
    @field:Json(name = "language")
    val lang: String?=null,
    @field:Json(name = "name")
    val name: String?=null,
    @field:Json(name = "englishName")
    val englishName: String?=null,
    @field:Json(name = "format")
    val format: String?=null,
    @field:Json(name = "type")
    val type: String?=null,
)
