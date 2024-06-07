package com.islamic.presentation

import app.cash.turbine.test
import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.mapper.PrayDTO
import com.islamic.domain.usecase.home.ILoadPrayForHomeUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HomeViewModelShould {
    private lateinit var homeViewModel: HomeViewModel
    private val iPrayForHomeUseCase = mock<ILoadPrayForHomeUseCase>()

    @Before
    fun setup() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `get result success from use case when it returns success state`() = runTest {
        whenever(iPrayForHomeUseCase.getPrayDTO()).thenReturn(flow {
            emit(ResultState.ResultSuccess(PrayDTO()))
        })
        homeViewModel = HomeViewModel(iPrayForHomeUseCase)
        advanceUntilIdle()
        assertEquals(PrayDTO(), homeViewModel.currentState.prayDTO)
    }

    @Test
    fun `get result error from use case when it returns error state`() = runTest {
        val error = TextWrapper.ResourceText(R.string.something_went_wrong)
        whenever(iPrayForHomeUseCase.getPrayDTO()).thenReturn(flow {
            emit(ResultState.ResultError(error))
        })
        homeViewModel = HomeViewModel(iPrayForHomeUseCase)
        advanceUntilIdle()
        assertEquals(error, homeViewModel.currentState.textWrapper)
    }

    @Test
    fun `toggle loading is working as expected`() = runTest {
        whenever(iPrayForHomeUseCase.getPrayDTO()).thenReturn(flow {
            emit(ResultState.ResultSuccess(PrayDTO()))
        })
        homeViewModel = HomeViewModel(iPrayForHomeUseCase)
        homeViewModel.state.test {
            val emission1 = awaitItem()
            assertEquals(false, emission1.isLoading)
            val emission2 = awaitItem()
            assertEquals(true, emission2.isLoading)
            val emission3 = awaitItem()
            assertEquals(false, emission3.isLoading)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}