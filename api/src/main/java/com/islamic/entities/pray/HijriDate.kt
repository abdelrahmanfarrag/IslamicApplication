package com.islamic.entities.pray

import com.squareup.moshi.Json

data class HijriDate(
    @field:Json(name = "hijri")
    val hijri: Hijri? = null,
) {

}