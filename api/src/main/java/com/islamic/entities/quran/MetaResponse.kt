package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("surahs")
    val surrah:Surrah?
)
