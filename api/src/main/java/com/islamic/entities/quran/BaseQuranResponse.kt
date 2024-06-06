package com.islamic.entities.quran

import com.google.gson.annotations.SerializedName

data class BaseQuranResponse<T>(
    @SerializedName("code")
    val code:Int?=null,
    @SerializedName("status")
    val status:Int?=null,
    @SerializedName("data")
    val data : ArrayList<T>?=null,
)
