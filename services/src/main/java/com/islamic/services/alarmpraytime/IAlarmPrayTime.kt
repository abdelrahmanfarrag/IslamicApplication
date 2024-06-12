package com.islamic.services.alarmpraytime

import java.time.LocalDateTime

interface IAlarmPrayTime {
    fun setAlarm()
    fun cancel(localDateTime: LocalDateTime)

}