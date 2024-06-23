package com.islamic.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalQuran(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val quranTafsirGson: String? = null,
    val quranMetaGson: String? = null,
    val quranSheikhGson: String? = null
)
