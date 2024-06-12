package com.islamic.services.alarmpraytime

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.TimeZone
import com.islamic.services.AlarmPrayReceiver
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject


class AlarmPrayTime @Inject constructor(
    private val alarmManager: AlarmManager,
    private val context: Context
) : IAlarmPrayTime {


    private val today: LocalDate = LocalDate.now(ZoneId.of(TimeZone.getDefault().id))
    private val midNightLocalDateTime = LocalDateTime.of(today, LocalTime.MIDNIGHT.plusMinutes(1))

    init {
        setAlarm()
    }

    override fun setAlarm() {
        val intent = Intent(context, AlarmPrayReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", "Welcome")
        }
        val time = Calendar.getInstance()
        time.set(Calendar.HOUR, 11)
        time.set(Calendar.MINUTE, 45)
        time.timeInMillis
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time.timeInMillis,
            PendingIntent.getBroadcast(
                context,
                midNightLocalDateTime.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(localDateTime: LocalDateTime) {
    }
}