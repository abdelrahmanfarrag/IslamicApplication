package com.islamic.remotedatasource.pray

import com.islamic.api.pray.PrayAPI
import com.islamic.di.qualifiers.PrayServer
import com.islamic.entities.pray.PrayResponse
import com.islamic.remotedatasource.networkcheck.ICheckNetworkAvailability
import com.islamic.utils.mapBasedOnNetworkState
import com.islamic.validateresponse.IValidateResponse
import com.islamic.validateresponse.ServerResponseState
import javax.inject.Inject

class PrayRemoteDataSource
@Inject constructor(
    @PrayServer private val prayAPI: PrayAPI,
    private val iCheckNetworkAvailability: ICheckNetworkAvailability,
    private val iValidateResponse: IValidateResponse
) : IPrayRemoteDataSource {
    override suspend fun getPrayTime(
        date: String,
        city: String,
        country: String
    ): ServerResponseState<PrayResponse> {
        return iValidateResponse.validateResponse(prayAPI.getAdhan(date, city, country))
            .mapBasedOnNetworkState(iCheckNetworkAvailability.isNetworkAvailable())
    }
}