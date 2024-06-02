package com.islamic.remotedatasource.quran

import com.islamic.entities.quran.BaseQuranResponse
import com.islamic.entities.quran.MetaResponse
import com.islamic.entities.quran.Quran
import com.islamic.entities.quran.QuranEditionType
import com.islamic.validateresponse.ServerResponseState
import retrofit2.Response

interface IQuranRemoteDataSource {
    suspend fun getQuranAvailableTafsirTypes(): ServerResponseState<BaseQuranResponse<QuranEditionType>>

    suspend fun getQuranAvailableSheikhAudios(): ServerResponseState<BaseQuranResponse<QuranEditionType>>

    suspend fun getQuranData(
        surrahNumber: Int,
        pathData: String
    ): ServerResponseState<BaseQuranResponse<Quran>>

    suspend fun getQuranMeta():ServerResponseState<BaseQuranResponse<MetaResponse>>
}