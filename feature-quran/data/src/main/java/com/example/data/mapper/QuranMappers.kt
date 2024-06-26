package com.example.data.mapper

import android.util.Log
import com.example.domain.entities.QuranDTO
import com.example.domain.entities.QuranMeta
import com.example.domain.entities.QuranSheikhAudio
import com.example.domain.entities.QuranTafsir
import com.example.domain.entities.SingleSurrah
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.islamic.entities.quran.MetaResponse
import com.islamic.entities.quran.QuranEditionType
import com.islamic.local.entities.LocalQuran
import java.lang.reflect.Type

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

inline fun <reified T> parseArray(json: String, typeToken: Type): T {
    val gson = GsonBuilder().create()
    return gson.fromJson<T>(json, typeToken)
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
    val type = object : TypeToken<List<QuranSheikhAudio>>() {}.type
    val result: List<QuranSheikhAudio> =
        parseArray<List<QuranSheikhAudio>>(json = this.quranSheikhGson ?: "", typeToken = type)
    val quranTafsirType = object : TypeToken<List<QuranTafsir>>() {}.type
    val quranTafsirResult: List<QuranTafsir> =
        parseArray<List<QuranTafsir>>(
            json = this.quranTafsirGson ?: "-",
            typeToken = quranTafsirType
        )

    val quranMetaType = object : TypeToken<QuranMeta>() {}.type
    val quranMetaResult: QuranMeta =
        parseArray<QuranMeta>(
            json = this.quranMetaGson ?: "-",
            typeToken = quranMetaType
        )
    return QuranDTO(
        quranSheikhAudios = result, quranMeta = quranMetaResult, quranTafsir = quranTafsirResult
    )
}