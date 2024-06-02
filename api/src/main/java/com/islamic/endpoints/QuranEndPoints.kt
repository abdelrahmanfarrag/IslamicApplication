package com.islamic.endpoints

object QuranEndPoints {
    const val SERVER_URL = "http://api.alquran.cloud/v1/"

    const val AVAILABLE_QURAN_AUDIOS = "edition/format/audio"
    const val AVAILABLE_QURAN_TAFSIR = "edition/type/tafsir"
    const val QURAN_META = "meta"
    const val QURAN_DATA = "{surah_number}/editions/{path}"
    const val QURAN_DATA_PATH = "path"
    const val SURAH_NUMBER = "surah_number"
}