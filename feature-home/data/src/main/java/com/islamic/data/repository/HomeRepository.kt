package com.islamic.data.repository

import com.islamic.data.mapper.mapToPray
import com.islamic.data.utils.mapToResultState
import com.islamic.domain.ResultState
import com.islamic.domain.mapper.Pray
import com.islamic.domain.repository.IHomeRepository
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val iPrayRemoteDataSource: IPrayRemoteDataSource
) : IHomeRepository {
    override suspend fun getPrayTime(
        data: String,
        city: String,
        country: String
    ): Flow<ResultState<Pray?>> {
        return iPrayRemoteDataSource.getPrayTime(data,city,country).mapToResultState {
           it?.mapToPray()
        }
    }
}