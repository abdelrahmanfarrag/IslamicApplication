package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class QuranEditionType(
    @SerializedName("identifier")
    val id: String? = null,
    @SerializedName("language")
    val lang: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("englishName")
    val englishName: String? = null,
    @SerializedName("format")
    val format: String? = null,
    @SerializedName("type")
    val type: String? = null,
)
