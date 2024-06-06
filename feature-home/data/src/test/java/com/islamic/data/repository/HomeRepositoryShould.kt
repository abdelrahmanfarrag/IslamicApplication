package com.islamic.data.repository

import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.domain.mapper.Pray
import com.islamic.domain.repository.IHomeRepository
import com.islamic.entities.pray.PrayResponse
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import com.islamic.validateresponse.ServerResponseState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@Suppress("UNCHECKED_CAST")
class HomeRepositoryShould {

    private lateinit var iHomeRepository: IHomeRepository
    private val iPrayRemoteDataSource = mock<IPrayRemoteDataSource>()

    private suspend fun initTests(serverResponseState: ServerResponseState<PrayResponse>): ResultState<Pray?> {
        whenever(iPrayRemoteDataSource.getPrayTime("", "", "")).thenReturn(
            serverResponseState
        )
        iHomeRepository = HomeRepository(iPrayRemoteDataSource)
        return iHomeRepository.getPrayTime("", "", "").first()
    }

    @Test
    fun `return the data state success when server response state is no internet connect`() =
        runTest {
            val result =
                initTests(ServerResponseState.NoInternetConnection as ServerResponseState<PrayResponse>)
            assertEquals(
                ResultState.ResultError<PrayResponse>(TextWrapper.ResourceText(R.string.no_internet)),
                result
            )
        }

    @Test
    fun `return the data state success when server response state is success`() = runTest {
        val result = initTests(ServerResponseState.StateSuccess(PrayResponse()))
        assertEquals(ResultState.ResultSuccess(Pray()), result)
    }

    @Test
    fun `return the data state error when server response status is error`() = runTest {
        val result =
            initTests(ServerResponseState.StateError(100) as ServerResponseState<PrayResponse>)
        assertEquals(
            ResultState.ResultError<PrayResponse>(TextWrapper.ResourceText(R.string.something_went_wrong)),
            result
        )
    }
}