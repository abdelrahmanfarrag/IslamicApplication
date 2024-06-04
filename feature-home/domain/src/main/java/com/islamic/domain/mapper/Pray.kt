package com.islamic.domain.mapper

data class Pray(
    val day: String?=null,
    val dayOfMonth: Int?=null,
    val monthName: String?=null,
    val fajr:String?=null,
    val dhuhr:String?=null,
    val asr:String?=null,
    val maghreb:String?=null,
    val isha:String?=null,
    val sunrise:String?=null
)
