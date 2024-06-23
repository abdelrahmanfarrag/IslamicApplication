package com.feature_quran.presentation.viewmodel

import app.cash.turbine.test
import com.example.domain.entities.QuranDTO
import com.example.domain.usecase.ILoadQuranInitialDataUseCase
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class QuranViewModelShould {
    private lateinit var quranViewModel: QuranViewModel
    private val iLoadQuranInitialDataUseCase = mock<ILoadQuranInitialDataUseCase>()

    @Before
    fun setup() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `set quranDTO when result state is success`() = runTest {
        val quranDto = QuranDTO()
        whenever(iLoadQuranInitialDataUseCase()).thenReturn(flow {
            emit(ResultState.ResultSuccess(quranDto))
        })
        quranViewModel = QuranViewModel(iLoadQuranInitialDataUseCase)
        advanceUntilIdle()
        assertEquals(quranDto, quranViewModel.currentState.quranDto)
    }

    @Test
    fun `set error text when result state is error`() = runTest {
        val error = TextWrapper.StringText("ERROR")
        whenever(iLoadQuranInitialDataUseCase()).thenReturn(flow {
            emit(ResultState.ResultError(error))
        })
        quranViewModel = QuranViewModel(iLoadQuranInitialDataUseCase)
        advanceUntilIdle()
        assertEquals(error, quranViewModel.currentState.errorText)
    }

    @Test
    fun `set loading in right manner`() = runTest {
        whenever(iLoadQuranInitialDataUseCase()).thenReturn(flow {
            emit(ResultState.ResultSuccess(QuranDTO()))
        })
        quranViewModel = QuranViewModel(iLoadQuranInitialDataUseCase)
        quranViewModel.state.test {
            val emitNo1 = awaitItem()
            assertEquals(false, emitNo1.isLoading)
            val emitNo2 = awaitItem()
            assertEquals(true, emitNo2.isLoading)
            val emitNo3 = awaitItem()
            assertEquals(false, emitNo3.isLoading)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}