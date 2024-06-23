package com.example.data.mapper

import com.example.domain.entities.QuranDTO
import com.example.domain.entities.QuranMeta
import com.example.domain.entities.QuranSheikhAudio
import com.example.domain.entities.QuranTafsir
import com.example.domain.entities.SingleSurrah
import com.google.gson.Gson
import com.islamic.entities.quran.MetaResponse
import com.islamic.entities.quran.QuranEditionType
import com.islamic.local.entities.LocalQuran

fun QuranEditionType.mapToQuranTafsir(): QuranTafsir {
    return QuranTafsir(
        name = this.name,
        id = this.id,
        language = this.lang,
        type = this.type
    )
}

fun QuranEditionType.mapToQuranSheikhAudio(): QuranSheikhAudio {
    return QuranSheikhAudio(
        name = this.name,
        id = this.id,
        language = this.lang,
        type = this.type
    )
}

fun MetaResponse.mapToQuranMeta(): QuranMeta {
    return QuranMeta(
        count = this.surrah?.count,
        surrahs = this.surrah?.references?.map {
            return@map SingleSurrah(
                name = it.name,
                number = it.number,
                ayahCount = it.countOfAyahs?.toInt()
            )
        }
    )
}

fun QuranDTO.mapToLocalQuran(gson: Gson): LocalQuran {
    return LocalQuran(
        quranSheikhGson = gson.toJson(this.quranSheikhAudios, List::class.java),
        quranMetaGson = gson.toJson(this.quranMeta, QuranMeta::class.java),
        quranTafsirGson = gson.toJson(this.quranTafsir, List::class.java)
    )
}

fun LocalQuran.mapToQuranDTO(gson: Gson): QuranDTO {
    return QuranDTO(
        quranSheikhAudios = gson.fromJson<ArrayList<QuranSheikhAudio>>(
            this.quranSheikhGson,
            ArrayList::class.java
        ), quranMeta = gson.fromJson(
            this.quranMetaGson,
            QuranMeta::class.java
        ), quranTafsir = gson.fromJson<ArrayList<QuranTafsir>>(
            this.quranTafsirGson,
            ArrayList::class.java
        )
    )
}