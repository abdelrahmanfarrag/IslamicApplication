@file:Suppress("UNCHECKED_CAST")

package com.feature_surrah.data.repository

import com.feature_surrah.domain.models.Surrah
import com.feature_surrah.domain.repository.ISurrahRepository
import com.islamic.domain.ResultState
import com.islamic.entities.quran.Ayah
import com.islamic.entities.quran.BaseQuranResponse
import com.islamic.entities.quran.Edition
import com.islamic.entities.quran.EditionTypes
import com.islamic.entities.quran.Quran
import com.islamic.entities.quran.QuranConstants
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import com.islamic.validateresponse.ServerResponseState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SurrahRepositoryShould {

    private lateinit var iSurrahRepository: ISurrahRepository
    private val iQuranRemoteDataSource = mock<IQuranRemoteDataSource>()

    private fun createSuccessResponse(
        ayahAudioArray: ArrayList<Ayah> = arrayListOf(),
        ayahTafsirArray: ArrayList<Ayah> = arrayListOf(),
        formatAudio: String = QuranConstants.AUDIO_TYPE,
        formatTafsir: String = QuranConstants.TEXT_TYPE
    ): ServerResponseState<BaseQuranResponse<ArrayList<Quran>>> {
        return ServerResponseState.StateSuccess(
            BaseQuranResponse(
                data = arrayListOf(
                    Quran(
                        0, "", "",
                        0, ayahAudioArray, Edition(
                            name = "",
                            format = formatAudio,
                            type = "",
                            editionTypes = EditionTypes.Quran
                        )
                    ),
                    Quran(
                        0, "test", "",
                        0, ayahTafsirArray, Edition(
                            name = "",
                            format = formatTafsir,
                            type = "",
                            editionTypes = EditionTypes.Quran
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `return success state when there is internet connection`() = runTest {
        whenever(iQuranRemoteDataSource.getQuranData(1, "p,a")).thenReturn(
            createSuccessResponse()
        )
        iSurrahRepository = SurrahRepository(iQuranRemoteDataSource)
        val result = iSurrahRepository.getSurrah(1, arrayListOf("p", "a")).first()
        assertEquals(
            ResultState.ResultSuccess(Surrah()),
            result
        )
    }

    @Test
    fun `return error state when there is any error happened`() = runTest {
        whenever(iQuranRemoteDataSource.getQuranData(1, "p,a")).thenReturn(
            ServerResponseState.NoInternetConnection as ServerResponseState<BaseQuranResponse<ArrayList<Quran>>>
        )
        iSurrahRepository = SurrahRepository(iQuranRemoteDataSource)
        val result = iSurrahRepository.getSurrah(1, arrayListOf("p", "a")).first()
        assertEquals(
            true,
            result is ResultState.ResultError
        )
    }

    @Test
    fun `merging data in right manner from audio and tafsir objects`() = runTest {
        whenever(iQuranRemoteDataSource.getQuranData(1, "p,a")).thenReturn(
            createSuccessResponse(
                ayahAudioArray = arrayListOf(
                    Ayah(
                        0,
                        "text",
                        "1",
                        "https://welcome.come"
                    )
                ),
                ayahTafsirArray = arrayListOf(
                    Ayah(null, "this is tafsir", "1", null)
                )
            )
        )
        iSurrahRepository = SurrahRepository(iQuranRemoteDataSource)
        val result = iSurrahRepository.getSurrah(1, arrayListOf("p", "a")).first()
        assertEquals(
            ResultState.ResultSuccess(
                Surrah(
                    surrahNumber = 0,
                    ayahCount = 0,
                    surrahName = "", ayahs = arrayListOf(
                        com.feature_surrah.domain.models.Ayah(
                            0, "this is tafsir",
                            "https://welcome.come",
                            "text"
                        )
                    )
                )
            ), result
        )
    }

    @Test
    fun `return error state in case of array of quran mapper incorrectly due to change in type`() =
        runTest {
            whenever(iQuranRemoteDataSource.getQuranData(1, "p,a")).thenReturn(
                createSuccessResponse(
                    ayahAudioArray = arrayListOf(
                        Ayah(
                            0,
                            "text",
                            "1",
                            "https://welcome.come"
                        )
                    ),
                    ayahTafsirArray = arrayListOf(
                        Ayah(null, "this is tafsir", "1", null)
                    ),
                    formatTafsir = "",
                    formatAudio = ""
                )
            )
            iSurrahRepository = SurrahRepository(iQuranRemoteDataSource)
            val result = iSurrahRepository.getSurrah(1, arrayListOf("p", "a")).first()
            assertEquals(
                true, result is ResultState.ResultError
            )
        }

}