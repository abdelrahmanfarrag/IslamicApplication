package com.islamic.entities.quran

import com.squareup.moshi.Json

data class Surrah(
    @field:Json(name = "count")
    val count:Int?,
    @field:Json(name = "references")
    val references:ArrayList<SurrahReference>?
)