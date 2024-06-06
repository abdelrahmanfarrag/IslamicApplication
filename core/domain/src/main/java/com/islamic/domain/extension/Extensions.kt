package com.islamic.domain.extension

import com.islamic.domain.Constants
import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

fun String?.convertToLocalDateTime(pattern: String=Constants.HH_MM_FORMAT,clock: Clock): LocalDateTime {
    val  fmt =  DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ISO_LOCAL_TIME)
        .parseDefaulting(ChronoField.YEAR, LocalDateTime.now(clock).year.toLong())
        .parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDateTime.now(clock).month.value.toLong())
        .parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDateTime.now(clock).dayOfMonth.toLong())
        .toFormatter()
    //val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    val localDateTime = LocalDateTime.parse(this, fmt)
    return localDateTime
}