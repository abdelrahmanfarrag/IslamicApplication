package com.islamic.entities.pray

import com.squareup.moshi.Json

data class PrayResponse(
    @field:Json(name = "code")
    val code: Int? = null,
    @field:Json(name = "status")
    val status: String? = null,
    @field:Json(name = "data")
    val prayData: PrayData? = null
)
