package com.islamic.data.repository

import com.islamic.data.mapper.mapToCachedPray
import com.islamic.data.mapper.mapToPray
import com.islamic.data.utils.mapToResultState
import com.islamic.domain.ResultState
import com.islamic.domain.mapper.Pray
import com.islamic.domain.repository.IHomeRepository
import com.islamic.local.localdatasource.IPrayLocalDataSource
import com.islamic.remotedatasource.pray.IPrayRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val iPrayRemoteDataSource: IPrayRemoteDataSource,
    private val iPrayLocalDataSource: IPrayLocalDataSource
) : IHomeRepository {
    override suspend fun getPrayTime(
        data: String,
        city: String,
        country: String
    ): Flow<ResultState<Pray?>> {
        val cachedPray = iPrayLocalDataSource.getPray()
        return cachedPray.map { localSource ->
            return@map if (localSource.isEmpty()) {
                val remoteSource = iPrayRemoteDataSource.getPrayTime(data, city, country).mapToResultState {
                    it?.mapToPray()
                }
                if (remoteSource is ResultState.ResultSuccess)
                    iPrayLocalDataSource.upsertPray(
                        remoteSource.result?.mapToCachedPray(
                            city,
                            country
                        )!!
                    )
                if (localSource.isNotEmpty())
                    return@map ResultState.ResultSuccess(localSource[0].mapToPray())
                else
                    return@map remoteSource
            } else {
                val result = localSource.first().mapToPray()
                ResultState.ResultSuccess(result)
            }
        }
    }
}