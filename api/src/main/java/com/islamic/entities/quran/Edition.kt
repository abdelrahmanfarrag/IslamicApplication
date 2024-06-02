package com.islamic.entities.quran

import com.squareup.moshi.Json

data class Edition(
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "format")
    val format:String?,
    val editionTypes: EditionTypes?
)
