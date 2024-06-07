package com.islamic.data.mapper

import com.islamic.domain.mapper.Pray
import com.islamic.entities.pray.PrayResponse

fun PrayResponse.mapToPray()= Pray(
    this.prayData?.adhanTimings?.fajr,
    this.prayData?.adhanTimings?.dhuhr,
    this.prayData?.adhanTimings?.asr,
    this.prayData?.adhanTimings?.maghrib,
    this.prayData?.adhanTimings?.isha,
    this.prayData?.adhanTimings?.sunrise
)