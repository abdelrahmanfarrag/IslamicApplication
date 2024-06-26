package com.example.domain.entities

data class QuranSheikhAudio(
    val name: String ?= "",
    val language: String ?= "",
    val type: String ?= "",
    val id: String ?= "",
    val isSelected:Boolean=false
)
