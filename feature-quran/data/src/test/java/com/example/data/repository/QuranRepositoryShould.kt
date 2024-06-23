@file:Suppress("UNCHECKED_CAST", "UNCHECKED_CAST")

package com.example.data.repository

import com.example.domain.entities.QuranDTO
import com.example.domain.repository.IQuranRepository
import com.google.gson.Gson
import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.entities.quran.BaseQuranResponse
import com.islamic.entities.quran.MetaResponse
import com.islamic.entities.quran.QuranEditionType
import com.islamic.local.entities.LocalQuran
import com.islamic.local.localdatasource.quran.IQuranLocalDataSource
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import com.islamic.validateresponse.ServerResponseState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class QuranRepositoryShould {

    private lateinit var iQuranRepository: IQuranRepository
    private val iQuranRemoteDataSource = mock<IQuranRemoteDataSource>()
    private val iLocalDataSource = mock<IQuranLocalDataSource>()


    private suspend fun initTests(
        metResponse: ServerResponseState<BaseQuranResponse<MetaResponse>>,
        tafsirResponse: ServerResponseState<BaseQuranResponse<List<QuranEditionType>>>,
        audioResponse: ServerResponseState<BaseQuranResponse<List<QuranEditionType>>>,
        localList: List<LocalQuran>
    ): ResultState<QuranDTO> {
        whenever(iQuranRemoteDataSource.getQuranMeta()).thenReturn(metResponse)
        whenever(iQuranRemoteDataSource.getQuranAvailableTafsirTypes()).thenReturn(tafsirResponse)
        whenever(iQuranRemoteDataSource.getQuranAvailableSheikhAudios()).thenReturn(audioResponse)
        whenever(iLocalDataSource.getQuranData()).thenReturn(flow { emit(localList) })
        iQuranRepository = QuranRepository(iQuranRemoteDataSource, iLocalDataSource, Gson())
        return iQuranRepository.getQuran().first()
    }

    @Test
    fun `return no internet connection when there is no data cached and APIS responded with no internet connection`() =
        runTest {
            val result =
                initTests(
                    ServerResponseState.NoInternetConnection as ServerResponseState<BaseQuranResponse<MetaResponse>>,
                    ServerResponseState.NoInternetConnection as ServerResponseState<BaseQuranResponse<List<QuranEditionType>>>,
                    ServerResponseState.StateSuccess(BaseQuranResponse(data = arrayListOf())),
                    arrayListOf()
                )
            assertEquals(
                ResultState.ResultError<QuranDTO>(
                    TextWrapper.ResourceText(R.string.no_internet)
                ), result
            )
        }

    @Test
    fun `return result success when there is cached data and APIS respond with error`() = runTest {
        val result =
            initTests(
                ServerResponseState.NoInternetConnection as ServerResponseState<BaseQuranResponse<MetaResponse>>,
                ServerResponseState.NoInternetConnection as ServerResponseState<BaseQuranResponse<List<QuranEditionType>>>,
                ServerResponseState.StateSuccess(BaseQuranResponse(data = arrayListOf())),
                arrayListOf(LocalQuran())
            )
        assertEquals(
            ResultState.ResultSuccess(
                QuranDTO(
                    quranMeta = null,
                    quranTafsir = null,
                    quranSheikhAudios = null
                )
            ), result
        )
    }

    @Test
    fun `return network response when there is no cached data and all APIS respond with success`() =
        runTest {
            val result = initTests(
                ServerResponseState.StateSuccess(data = BaseQuranResponse(data = MetaResponse(null))),
                ServerResponseState.StateSuccess(data = BaseQuranResponse(data = arrayListOf())),
                ServerResponseState.StateSuccess(data = BaseQuranResponse(data = arrayListOf())),
                arrayListOf()
            )
            assertEquals(ResultState.ResultSuccess(QuranDTO()), result)
        }
}
