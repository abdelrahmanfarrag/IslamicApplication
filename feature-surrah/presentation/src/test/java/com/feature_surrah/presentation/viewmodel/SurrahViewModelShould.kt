package com.feature_surrah.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.feature_surrah.domain.models.Surrah
import com.feature_surrah.domain.usecase.IGetSurrahUseCase
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
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

class SurrahViewModelShould {
    private lateinit var surrahViewModel: SurrahViewModel
    private val iGetSurrahUseCase = mock<IGetSurrahUseCase>()
    private val mSavedStatHandle = mock<SavedStateHandle>()
    private val fakeExoPlayer = FakeExoPlayer()

    @Before
    fun setup() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    private suspend fun initTests(resultState: ResultState<Surrah>) {
        whenever(mSavedStatHandle.get<Int>("number")).thenReturn(1)
        whenever(mSavedStatHandle.get<String>("audioId")).thenReturn("aId")
        whenever(mSavedStatHandle.get<String>("tafsirId")).thenReturn("tId")
        whenever(
            iGetSurrahUseCase.getSurrah(
                1, arrayListOf(
                    "aId",
                    "tId"
                )
            )
        ).thenReturn(flow {
            emit(resultState)
        })
        surrahViewModel = SurrahViewModel(iGetSurrahUseCase, mSavedStatHandle,fakeExoPlayer)
    }

    @Test
    fun `save arguments successfully in state of viewmodel`() = runTest {
        initTests(ResultState.ResultSuccess(Surrah()))
        advanceUntilIdle()
        assertEquals(1, surrahViewModel.currentState.surrahNumber)
        assertEquals("aId", surrahViewModel.currentState.audioId)
        assertEquals("tId", surrahViewModel.currentState.tafsirId)
    }


    @Test
    fun `set the Surrah object successfully when result state is success`() = runTest {
        val surrah = Surrah()
        initTests(ResultState.ResultSuccess(surrah))
        advanceUntilIdle()
        assertEquals(Surrah(), surrahViewModel.currentState.surrah)
    }

    @Test
    fun `set error obtained from API response`() = runTest {
        val errorText = TextWrapper.StringText("something went wrong!")
        initTests(ResultState.ResultError(errorText))
        advanceUntilIdle()
        assertEquals(errorText, surrahViewModel.currentState.errorText)
    }

    @Test
    fun `toggle Loading working as expected`() = runTest {
        initTests(ResultState.ResultSuccess(Surrah()))
        surrahViewModel.state.test {
            advanceUntilIdle()
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