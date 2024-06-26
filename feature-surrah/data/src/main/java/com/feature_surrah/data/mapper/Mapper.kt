package com.feature_surrah.data.mapper

import com.feature_surrah.domain.models.Ayah
import com.feature_surrah.domain.models.Surrah
import com.islamic.entities.quran.Quran
import com.islamic.entities.quran.QuranConstants

fun ArrayList<Quran>.mapToSurrah(): Surrah? {
    val audioSurrah = find { quran ->
        quran.edition?.format == QuranConstants.AUDIO_TYPE
    }
    val tafsirSurrah = find { quran ->
        quran.edition?.format == QuranConstants.TEXT_TYPE
    }
    return if (audioSurrah == null && tafsirSurrah == null)
        null
    else {
        val ayahs = audioSurrah?.ayahs?.zip(tafsirSurrah?.ayahs ?: arrayListOf()) { audio, tafsir ->
            return@zip Ayah(
                ayahNumber = audio.number ?: tafsir.number,
                ayahAudioURL = audio.audio,
                ayahTafsirText = tafsir.text,
                ayah = audio.text
            )
        } as ArrayList
        Surrah(
            surrahNumber = audioSurrah.number ?: tafsirSurrah?.number,
            surrahName = audioSurrah.name ?: tafsirSurrah?.name,
            ayahCount = audioSurrah.countOfAyahs ?: tafsirSurrah?.countOfAyahs,
            ayahs = ayahs
        )
    }
}