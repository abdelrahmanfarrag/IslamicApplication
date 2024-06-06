package com.islamic.domain.usecase.date

import com.islamic.domain.Constants
import com.islamic.domain.utils.MutableClock
import com.islamic.domain.utils.advanceTimeBy
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.hours

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentDateShould {

    private lateinit var iGetCurrentDateUseCase: IGetCurrentDateUseCase

    @Test
    fun `return the write date format`() = runTest {
        val clock = MutableClock(Clock.systemDefaultZone())
        iGetCurrentDateUseCase = GetCurrentDateUseCase(clock)
        advanceTimeBy(3.hours, clock)
        val result = iGetCurrentDateUseCase.getCurrentDate()
        assertEquals(
            LocalDateTime.now(clock).format(DateTimeFormatter.ofPattern(Constants.DD_MM_YYYY_FORMAT)),
            result
        )

    }
}