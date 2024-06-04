package com.islamic.entities.pray

import com.squareup.moshi.Json

data class PrayData(
    @field:Json(name = "date")
    val hijri: HijriDate? = null,
    @field:Json(name = "timings")
    val adhanTimings: AdhanTimings?=null,

)
