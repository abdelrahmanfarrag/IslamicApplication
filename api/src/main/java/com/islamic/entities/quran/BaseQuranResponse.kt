package com.islamic.entities.quran

import com.squareup.moshi.Json

data class BaseQuranResponse<T>(
    @field:Json(name="code")
    val code:Int?=null,
    @field:Json(name="status")
    val status:Int?=null,
    @field:Json(name="data")
    val data : ArrayList<T>?=null,
)
