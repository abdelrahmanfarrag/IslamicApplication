package com.islamic.domain.usecase.hijridate

import com.islamic.domain.Constants
import com.islamic.domain.extension.replaceEnglishNumberWithArabic
import java.time.LocalDateTime
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


class GetHijriDate @Inject constructor() : IGetHijriDate {
    override fun getHijriDate(): String {
        val gregorianDate: LocalDateTime = LocalDateTime.now()
        val islamicDate: HijrahDate = HijrahDate.from(gregorianDate)
        val dateFormatted =islamicDate.format(DateTimeFormatter.ofPattern(Constants.MMM_DD_YYYY_FORMAT,Locale("ar")))

        return dateFormatted.replaceEnglishNumberWithArabic()
    }
}