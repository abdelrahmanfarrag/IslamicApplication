package com.islamic.domain.usecase.date

import com.islamic.domain.Constants.DD_MM_YYYY_FORMAT
import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetCurrentDateUseCase @Inject constructor(
    private val clock: Clock
) : IGetCurrentDateUseCase {
    override fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY_FORMAT)
        val time = LocalDateTime.now(clock).format(formatter)
        return time
    }
}