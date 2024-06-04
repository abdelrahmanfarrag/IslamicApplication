package com.islamic.api.pray

import com.islamic.endpoints.PrayEndPoints
import com.islamic.entities.pray.PrayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PrayAPI {

    @GET(PrayEndPoints.TIMING)
    suspend fun getAdhan(
        @Path(PrayEndPoints.DATE) data: String,
        @Query(PrayEndPoints.CITY) city:String,
        @Query(PrayEndPoints.COUNTRY) country:String,
    ) : Response<PrayResponse>

}