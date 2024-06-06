package com.islamic.entities.pray

import com.google.gson.annotations.SerializedName

data class HijriDate(
    @SerializedName("hijri")
    val hijri: Hijri? = null,
) {

}