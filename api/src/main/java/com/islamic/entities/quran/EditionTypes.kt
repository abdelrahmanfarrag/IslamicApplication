package com.islamic.entities.quran

sealed class EditionTypes(val type: String) {
    object Quran : EditionTypes(QuranConstants.QURAN_TYPE)
    object Audio : EditionTypes(QuranConstants.AUDIO_TYPE)
    object Tafsir : EditionTypes(QuranConstants.TAFSIR_TYPE)

    companion object {
        fun getEditType(type: String): EditionTypes {
            return when (type) {
                QuranConstants.QURAN_TYPE -> Quran
                QuranConstants.AUDIO_TYPE -> Audio
                QuranConstants.TAFSIR_TYPE -> Tafsir
                else -> Quran
            }
        }
    }
}