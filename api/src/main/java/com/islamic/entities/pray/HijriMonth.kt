package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class HijriMonth(
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("ar")
    val arabicName: String? = null
)
