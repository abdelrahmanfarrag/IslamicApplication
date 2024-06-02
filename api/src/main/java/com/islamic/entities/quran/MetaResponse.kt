package com.islamic.entities.quran

import com.squareup.moshi.Json

data class MetaResponse(
    @field:Json(name = "surrahs")
    val surrah:Surrah?
)
