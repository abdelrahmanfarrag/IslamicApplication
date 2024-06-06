package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("surrahs")
    val surrah:Surrah?
)
