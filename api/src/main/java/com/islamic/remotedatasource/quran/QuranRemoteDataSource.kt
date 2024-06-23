package com.islamic.remotedatasource.quran

import com.islamic.api.quran.QuranAPI
import com.islamic.di.qualifiers.QuranServer
import com.islamic.entities.quran.BaseQuranResponse
import com.islamic.entities.quran.MetaResponse
import com.islamic.entities.quran.Quran
import com.islamic.entities.quran.QuranEditionType
import com.islamic.remotedatasource.networkcheck.ICheckNetworkAvailability
import com.islamic.utils.mapBasedOnNetworkState
import com.islamic.validateresponse.IValidateResponse
import com.islamic.validateresponse.ServerResponseState
import javax.inject.Inject

class QuranRemoteDataSource @Inject constructor(
    private val iCheckNetworkAvailability: ICheckNetworkAvailability,
    private val iValidateResponse: IValidateResponse,
    @QuranServer private val quranAPI: QuranAPI
) : IQuranRemoteDataSource {
    override suspend fun getQuranAvailableTafsirTypes(): ServerResponseState<BaseQuranResponse<List<QuranEditionType>>> {
        val isNetworkAvailable = iCheckNetworkAvailability.isNetworkAvailable()
        return iValidateResponse.validateResponse(quranAPI.getQuranAvailableTafsirTypes())
            .mapBasedOnNetworkState(isNetworkAvailable)
    }

    override suspend fun getQuranAvailableSheikhAudios(): ServerResponseState<BaseQuranResponse<List<QuranEditionType>>> {
        val isNetworkAvailable = iCheckNetworkAvailability.isNetworkAvailable()
        return iValidateResponse.validateResponse(quranAPI.getQuranAvailableSheikhAudios())
            .mapBasedOnNetworkState(isNetworkAvailable)
    }

    override suspend fun getQuranData(
        surrahNumber: Int,
        pathData: String
    ): ServerResponseState<BaseQuranResponse<Quran>> {
        val isNetworkAvailable = iCheckNetworkAvailability.isNetworkAvailable()
        return iValidateResponse.validateResponse(quranAPI.getQuranData(surrahNumber, pathData))
            .mapBasedOnNetworkState(isNetworkAvailable)
    }

    override suspend fun getQuranMeta(): ServerResponseState<BaseQuranResponse<MetaResponse>> {
        val isNetworkAvailable = iCheckNetworkAvailability.isNetworkAvailable()
        return iValidateResponse.validateResponse(quranAPI.getQuranMeta())
            .mapBasedOnNetworkState(isNetworkAvailable)
    }
}