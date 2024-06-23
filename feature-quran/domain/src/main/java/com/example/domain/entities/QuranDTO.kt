package com.example.domain.entities

data class QuranDTO(
    val quranMeta: QuranMeta ?= QuranMeta(),
    val quranSheikhAudios: List<QuranSheikhAudio> ?= arrayListOf(),
    val quranTafsir: List<QuranTafsir> ?= arrayListOf()
)
