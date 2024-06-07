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

fun String?.replaceEnglishNumberWithArabic():String{
    val newValue: String = ((this + "")
        .replace("1".toRegex(), "١").replace("2".toRegex(), "٢")
        .replace("3".toRegex(), "٣").replace("4".toRegex(), "٤")
        .replace("5".toRegex(), "٥").replace("6".toRegex(), "٦")
        .replace("7".toRegex(), "٧").replace("8".toRegex(), "٨")
        .replace("9".toRegex(), "٩").replace("0".toRegex(), "٠"))
    return newValue
}