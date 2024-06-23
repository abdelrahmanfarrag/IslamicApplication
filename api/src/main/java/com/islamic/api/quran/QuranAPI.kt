package com.islamic.api.quran

import com.islamic.endpoints.QuranEndPoints
import com.islamic.entities.quran.BaseQuranResponse
import com.islamic.entities.quran.MetaResponse
import com.islamic.entities.quran.Quran
import com.islamic.entities.quran.QuranEditionType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranAPI {
    @GET(QuranEndPoints.AVAILABLE_QURAN_TAFSIR)
    suspend fun getQuranAvailableTafsirTypes(): Response<BaseQuranResponse<List<QuranEditionType>>>

    @GET(QuranEndPoints.AVAILABLE_QURAN_AUDIOS)
    suspend fun getQuranAvailableSheikhAudios(): Response<BaseQuranResponse<List<QuranEditionType>>>

    @GET(QuranEndPoints.QURAN_DATA)
    suspend fun getQuranData(
        @Path(QuranEndPoints.SURAH_NUMBER) number: Int,
        @Path(QuranEndPoints.QURAN_DATA_PATH) pathData: String
    ): Response<BaseQuranResponse<Quran>>

    @GET(QuranEndPoints.QURAN_META)
    suspend fun getQuranMeta(): Response<BaseQuranResponse<MetaResponse>>
}