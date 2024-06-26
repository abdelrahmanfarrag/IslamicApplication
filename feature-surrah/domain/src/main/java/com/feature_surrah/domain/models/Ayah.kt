package com.feature_surrah.domain.models

data class Ayah(
    val ayahNumber: Int ?= 0,
    val ayahTafsirText: String ?= "",
    val ayahAudioURL: String ?= "",
    val ayah: String ?= ""
)
