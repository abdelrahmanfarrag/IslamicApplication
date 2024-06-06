package com.islamic.domain.usecase

import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.mapper.Pray
import com.islamic.domain.mapper.PrayDTO
import com.islamic.domain.mapper.SinglePray
import com.islamic.domain.mapper.SkyState
import com.islamic.domain.usecase.praytimes.IGetPrayTimeUseCase
import com.islamic.domain.utils.MutableClock
import com.islamic.domain.utils.advanceTimeBy
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.Clock
import kotlin.time.Duration.Companion.hours

@OptIn(ExperimentalCoroutinesApi::class)
class LoadPrayForHomeUseCaseShould {

    private lateinit var iLoadPrayForHomeUseCase: ILoadPrayForHomeUseCase
    private val getPrayTimeUseCase = mock<IGetPrayTimeUseCase>()

    @Test
    fun `get the same state from getPrayTime UseCase`() = runTest {
        val clock = MutableClock(Clock.systemDefaultZone())
        whenever(getPrayTimeUseCase.getPrayTime("", "", "")).thenReturn(flow {
            emit(
                ResultState.ResultSuccess(
                    Pray(
                        "",
                        1,
                        "ss",
                        "04:08",
                        "12:54",
                        "16:30",
                        "19:54",
                        "21:27",
                        "05:53"
                    )
                )
            )
        })
        iLoadPrayForHomeUseCase = LoadPrayForHomeUseCase(getPrayTimeUseCase, clock)
        val result = iLoadPrayForHomeUseCase.getPrayDTO("", "", "").first()
        assertEquals(true, result.isResultSuccess())
    }

    @Test
    fun `return the right mapper after obtain pray times`() = runTest {
        val clock = MutableClock(Clock.systemDefaultZone())
        advanceTimeBy(3.hours, clock)
        whenever(getPrayTimeUseCase.getPrayTime("", "", "")).thenReturn(flow {
            emit(
                ResultState.ResultSuccess(
                    Pray(
                        "today",
                        1,
                        "ss",
                        "04:08",
                        "12:54",
                        "16:30",
                        "19:54",
                        "21:27",
                        "05:53"
                    )
                )
            )
        })
        iLoadPrayForHomeUseCase = LoadPrayForHomeUseCase(getPrayTimeUseCase, clock)
        val result = iLoadPrayForHomeUseCase.getPrayDTO("", "", "").first()
        val resultSuccess = (result as ResultState.ResultSuccess<PrayDTO>).result
        val fajrPray = resultSuccess?.prays?.find {
            it.prayName == TextWrapper.ResourceText(com.islamic.core_domain.R.string.fajr)
        }
        assertEquals(
            SinglePray.Fajr(
                fajrPray?.prayTime!!,
                fajrPray.isNextPray,
                fajrPray.timeAfString
            ), fajrPray
        )
        assertEquals(SkyState.BETWEEN_DHUHR_MAGHREB, resultSuccess.skyState)
        assertEquals(1, resultSuccess.dayOfMonth)
        assertEquals("today", resultSuccess.day)
        assertEquals(6, resultSuccess.prays.size)

        assertEquals("ss", resultSuccess.monthName)
        assertEquals(resultSuccess.prays.find {
            it.isNextPray
        }, resultSuccess.nextPray)
    }

    @Test
    fun `return the same error when error happens`()= runTest {
        val clock = MutableClock(Clock.systemDefaultZone())
        advanceTimeBy(3.hours, clock)
        whenever(getPrayTimeUseCase.getPrayTime("", "", "")).thenReturn(flow {
            emit(
                ResultState.ResultError(TextWrapper.StringText("exception"))
            )
        })
        iLoadPrayForHomeUseCase = LoadPrayForHomeUseCase(getPrayTimeUseCase, clock)
        val result = iLoadPrayForHomeUseCase.getPrayDTO("", "", "").first()
        val resultError = (result as ResultState.ResultError<PrayDTO>).textWrapper
        assertEquals(TextWrapper.StringText("exception"),resultError)
    }


}