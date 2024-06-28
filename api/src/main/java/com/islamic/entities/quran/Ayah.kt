package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class Ayah(
    @SerializedName("numberInSurah")
    val number: Int?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("juz")
    val juz: String?,
    @SerializedName("audio")
    val audio: String?,
)