package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class Edition(
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("format")
    val format: String?,
    val editionTypes: EditionTypes?
)
