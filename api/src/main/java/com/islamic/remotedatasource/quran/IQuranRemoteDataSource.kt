package com.islamic.remotedatasource.quran

import com.islamic.entities.quran.BaseQuranResponse
import com.islamic.entities.quran.MetaResponse
import com.islamic.entities.quran.Quran
import com.islamic.entities.quran.QuranEditionType
import com.islamic.validateresponse.ServerResponseState

interface IQuranRemoteDataSource {
    suspend fun getQuranAvailableTafsirTypes(): ServerResponseState<BaseQuranResponse<List<QuranEditionType>>>

    suspend fun getQuranAvailableSheikhAudios(): ServerResponseState<BaseQuranResponse<List<QuranEditionType>>>

    suspend fun getQuranData(
        surrahNumber: Int,
        pathData: String
    ): ServerResponseState<BaseQuranResponse<ArrayList<Quran>>>

    suspend fun getQuranMeta():ServerResponseState<BaseQuranResponse<MetaResponse>>
}