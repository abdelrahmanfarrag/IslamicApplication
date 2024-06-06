package com.islamic.domain.mapper

import com.islamic.domain.ResultState
import com.islamic.domain.extension.convertToLocalDateTime
import java.time.Clock
import java.time.LocalDateTime


fun ResultState<Pray?>.mapToPrayDTO(clock: Clock = Clock.systemDefaultZone()): ResultState<PrayDTO> {
    return when (this) {
        is ResultState.ResultSuccess<Pray?> -> {
            //Convert times obtained from API to localDateTime
            val fajirLocalDateTime = result?.fajr.convertToLocalDateTime(clock = clock)
            val shroukLocalDateTime = result?.sunrise.convertToLocalDateTime(clock = clock)
            val dhuhrLocalDateTime = result?.dhuhr.convertToLocalDateTime(clock = clock)
            val asrLocalDateTime = result?.asr.convertToLocalDateTime(clock = clock)
            val maghrebDateTime = result?.maghreb.convertToLocalDateTime(clock = clock)
            val ishaLocalDateTime = result?.isha.convertToLocalDateTime(clock = clock)

            //Comparing to obtain next pray
            val localDateTime = LocalDateTime.now(clock)
            val isNextFajr = localDateTime.isBefore(fajirLocalDateTime)
            val isNextShrouk =
                localDateTime.isAfter(fajirLocalDateTime) && localDateTime.isBefore(
                    shroukLocalDateTime
                )
            val isNextDhuhr =
                localDateTime.isAfter(shroukLocalDateTime) && localDateTime.isBefore(
                    dhuhrLocalDateTime
                )
            val isNextAsr =
                localDateTime.isAfter(dhuhrLocalDateTime) && localDateTime.isBefore(asrLocalDateTime)
            val isNextMaghreb =
                localDateTime.isAfter(asrLocalDateTime) && localDateTime.isBefore(maghrebDateTime)
            val isNextIsha =
                localDateTime.isAfter(maghrebDateTime) && localDateTime.isBefore(ishaLocalDateTime)

            //To set SkyState
            val prays = arrayListOf(
                SinglePray.Fajr(fajirLocalDateTime, isNextFajr, result?.fajr),
                SinglePray.Shrouk(shroukLocalDateTime, isNextShrouk, result?.sunrise),
                SinglePray.Dhuhr(dhuhrLocalDateTime, isNextDhuhr, result?.dhuhr),
                SinglePray.Asr(asrLocalDateTime, isNextAsr, result?.asr),
                SinglePray.Maghreb(maghrebDateTime, isNextMaghreb, result?.maghreb),
                SinglePray.Isha(ishaLocalDateTime, isNextIsha, result?.isha),
            )
            val nextPray = prays.find {
                it.isNextPray
            }
            val skyState = when (nextPray) {
                is SinglePray.Maghreb, is SinglePray.Asr -> SkyState.BETWEEN_DHUHR_MAGHREB
                is SinglePray.Fajr, is SinglePray.Shrouk -> SkyState.AFTER_ISHA
                is SinglePray.Isha -> SkyState.BETWEEN_MAGHREB_ISHA
                is SinglePray.Dhuhr -> SkyState.BETWEEN_SHROUK_DHUHR
                else -> null
            }
            return ResultState.ResultSuccess(
                PrayDTO(
                    prays,
                    skyState = skyState,
                    nextPray = nextPray,
                    day = result?.day,
                    monthName = result?.monthName,
                    dayOfMonth = result?.dayOfMonth
                )
            )
        }

        is ResultState.ResultError<Pray?> -> ResultState.ResultError(this.textWrapper)
    }
}

