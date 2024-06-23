package com.example.domain.entities

data class QuranMeta(
    val count: Int ?= null,
    val surrahs: List<SingleSurrah> ?= null
)
