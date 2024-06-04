package com.islamic.remotedatasource.pray

import com.islamic.entities.pray.PrayResponse
import com.islamic.validateresponse.ServerResponseState

interface IPrayRemoteDataSource {
    suspend fun getPrayTime(
        date: String,
        city: String,
        country: String
    ): ServerResponseState<PrayResponse>
}