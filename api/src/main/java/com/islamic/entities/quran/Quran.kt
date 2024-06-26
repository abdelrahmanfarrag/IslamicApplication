package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class Quran(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("revelationType")
    val revealType: String?,
    @SerializedName("numberOfAyahs")
    val countOfAyahs: Int?,
    @SerializedName("ayahs")
    val ayahs: ArrayList<Ayah>?,
    @SerializedName("edition")
    val edition: Edition?
)
