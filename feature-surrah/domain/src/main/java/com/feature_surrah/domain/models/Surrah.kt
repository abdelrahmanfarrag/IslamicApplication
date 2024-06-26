package com.feature_surrah.domain.models

data class Surrah(
    val surrahNumber: Int ?= 0,
    val surrahName: String ?= "",
    val ayahCount: Int ?= 0,
    val ayahs: ArrayList<Ayah> ?= arrayListOf()
)
