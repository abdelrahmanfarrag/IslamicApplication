package com.islamic.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedPray(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val fajr: String? = null,
    val dhuhr: String? = null,
    val asr: String? = null,
    val maghreb: String? = null,
    val isha: String? = null,
    val sunrise: String? = null,
    val city: String? = null,
    val country: String? = null
)