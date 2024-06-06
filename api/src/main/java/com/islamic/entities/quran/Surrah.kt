package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class Surrah(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("references")
    val references: ArrayList<SurrahReference>?
)