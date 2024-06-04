package com.islamic.api.remotedatasource.quran

import com.islamic.api.quran.QuranAPI
import com.islamic.api.validateresponse.ResponseHelper
import com.islamic.entities.quran.BaseQuranResponse
import com.islamic.remotedatasource.networkcheck.ICheckNetworkAvailability
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import com.islamic.remotedatasource.quran.QuranRemoteDataSource
import com.islamic.validateresponse.IValidateResponse
import com.islamic.validateresponse.ServerResponseState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class QuranRemoteDataSourceShould {

    private lateinit var iQuranRemoteDataSource: IQuranRemoteDataSource
    private val checkNetworkState = mock<ICheckNetworkAvailability>()
    private val iValidateResponse = mock<IValidateResponse>()
    private val quranAPI = mock<QuranAPI>()

    private suspend fun initTests(isNetworkAvailable: Boolean) {
        whenever(checkNetworkState.isNetworkAvailable()).thenReturn(isNetworkAvailable)
        whenever(iValidateResponse.validateResponse(quranAPI.getQuranAvailableTafsirTypes())).thenReturn(
            ServerResponseState.StateSuccess(
                BaseQuranResponse()
            )
        )
        iQuranRemoteDataSource =
            QuranRemoteDataSource(checkNetworkState, iValidateResponse, quranAPI)
    }

    @Test
    fun `return the response state when network is available`() = runTest {
        initTests(true)
        assertEquals(
            ServerResponseState.StateSuccess(
                BaseQuranResponse<Any>()
            ), iQuranRemoteDataSource.getQuranAvailableTafsirTypes()
        )
    }

    @Test
    fun `return the ServerResponseState as NoInternetConnection when network is NOT available`() =
        runTest {
            initTests(false)
            assertEquals(
                ServerResponseState.NoInternetConnection,
                iQuranRemoteDataSource.getQuranAvailableTafsirTypes()
            )
        }

    @Test
    fun `return the response state when network is available for SheikhAudios API`() = runTest {
        initTests(true)
        assertEquals(
            ServerResponseState.StateSuccess(
                BaseQuranResponse<Any>()
            ), iQuranRemoteDataSource.getQuranAvailableSheikhAudios()
        )
    }

    @Test
    fun `return the ServerResponseState as NoInternetConnection when network is NOT available for SheikhAudios API`() =
        runTest {
            initTests(false)
            assertEquals(
                ServerResponseState.NoInternetConnection,
                iQuranRemoteDataSource.getQuranAvailableSheikhAudios()
            )
        }

    @Test
    fun `return the response state when network is available for QuranData API`() = runTest {
        initTests(true)
        assertEquals(
            ServerResponseState.StateSuccess(
                BaseQuranResponse<Any>()
            ), iQuranRemoteDataSource.getQuranData(1, "path")
        )
    }

    @Test
    fun `return the ServerResponseState as NoInternetConnection when network is NOT available for QuranData API`() =
        runTest {
            initTests(false)
            assertEquals(
                ServerResponseState.NoInternetConnection,
                iQuranRemoteDataSource.getQuranData(1, "path")
            )
        }

    @Test
    fun `return the response state when network is available for QuranMeta API`() = runTest {
        initTests(true)
        assertEquals(
            ServerResponseState.StateSuccess(
                BaseQuranResponse<Any>()
            ), iQuranRemoteDataSource.getQuranMeta()
        )
    }

    @Test
    fun `return the ServerResponseState as NoInternetConnection when network is NOT available for QuranMeta API`() =
        runTest {
            initTests(false)
            assertEquals(
                ServerResponseState.NoInternetConnection,
                iQuranRemoteDataSource.getQuranMeta()
            )
        }
}