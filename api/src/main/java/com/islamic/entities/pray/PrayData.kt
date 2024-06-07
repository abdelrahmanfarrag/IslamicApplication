package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class PrayData(
    @SerializedName("timings")
    val adhanTimings: AdhanTimings? = null
)
