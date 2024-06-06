package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class SurrahReference(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("numberOfAyahs")
    val countOfAyahs: String?
)