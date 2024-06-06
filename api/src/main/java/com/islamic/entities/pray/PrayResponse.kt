package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class PrayResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("data")
    val prayData: PrayData? = null
)
