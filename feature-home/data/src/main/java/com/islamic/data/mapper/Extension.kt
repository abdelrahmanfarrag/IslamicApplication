package com.islamic.data.mapper

import com.islamic.domain.mapper.Pray
import com.islamic.entities.pray.PrayResponse
import com.islamic.local.entities.CachedPray

fun PrayResponse.mapToPray() = Pray(
    this.prayData?.adhanTimings?.fajr,
    this.prayData?.adhanTimings?.dhuhr,
    this.prayData?.adhanTimings?.asr,
    this.prayData?.adhanTimings?.maghrib,
    this.prayData?.adhanTimings?.isha,
    this.prayData?.adhanTimings?.sunrise
)

fun Pray.mapToCachedPray(city: String, country: String) = CachedPray(
    fajr = fajr,
    sunrise = sunrise,
    dhuhr = dhuhr,
    asr = asr,
    maghreb = maghreb,
    isha = isha,
    city = city,
    country = country
)

fun CachedPray.mapToPray() = Pray(
    fajr = fajr,
    sunrise = sunrise,
    dhuhr = dhuhr,
    asr = asr,
    maghreb = maghreb,
    isha = isha,
)