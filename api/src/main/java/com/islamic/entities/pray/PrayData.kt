package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class PrayData(
    @SerializedName("date")
    val hijriDate: HijriDate? = null,
    @SerializedName("timings")
    val adhanTimings: AdhanTimings?=null,

)
