package com.islamic.api.remotedatasource.pray

import com.islamic.api.pray.PrayAPI
import com.islamic.entities.pray.PrayResponse
import com.islamic.remotedatasource.networkcheck.ICheckNetworkAvailability
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import com.islamic.remotedatasource.pray.PrayRemoteDataSource
import com.islamic.validateresponse.IValidateResponse
import com.islamic.validateresponse.ServerResponseState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PrayRemoteDataSourceShould {

    private lateinit var iPrayRemoteDataSource: IPrayRemoteDataSource
    private val networkAvailability = mock<ICheckNetworkAvailability>()
    private val prayAPI = mock<PrayAPI>()
    private val validateRemoteResponse = mock<IValidateResponse>()

    private suspend fun initTests(isNetworkAvailable: Boolean) {
        whenever(networkAvailability.isNetworkAvailable()).thenReturn(isNetworkAvailable)
        whenever(validateRemoteResponse.validateResponse(prayAPI.getAdhan("", "", ""))).thenReturn(
            ServerResponseState.StateSuccess(PrayResponse())
        )
        iPrayRemoteDataSource =
            PrayRemoteDataSource(prayAPI, networkAvailability, validateRemoteResponse)
    }

    @Test
    fun `return pray times when there is internet connection`() = runTest {
        initTests(true)
        assertEquals(
            ServerResponseState.StateSuccess(PrayResponse()),
            iPrayRemoteDataSource.getPrayTime("", "", "")
        )
    }

    @Test
    fun `return no internet connection in case there is no connection`() = runTest {
        initTests(false)
        assertEquals(
            ServerResponseState.NoInternetConnection,
            iPrayRemoteDataSource.getPrayTime("", "", "")
        )
    }
}