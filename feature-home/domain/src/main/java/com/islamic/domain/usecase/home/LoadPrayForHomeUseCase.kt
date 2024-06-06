package com.islamic.domain.usecase.home

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.PrayDTO
import com.islamic.domain.mapper.mapToPrayDTO
import com.islamic.domain.usecase.date.IGetCurrentDateUseCase
import com.islamic.domain.usecase.location.IGetUserLocation
import com.islamic.domain.usecase.praytimes.IGetPrayTimeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.Clock
import javax.inject.Inject

class LoadPrayForHomeUseCase @Inject constructor(
    private val iGetPrayTimeUseCase: IGetPrayTimeUseCase,
    private val iGetUserLocation: IGetUserLocation,
    private val iGetCurrentDateUseCase: IGetCurrentDateUseCase,
    private val clock: Clock
) : ILoadPrayForHomeUseCase {
    override suspend fun getPrayDTO(
    ): Flow<ResultState<PrayDTO>> {
        val date = iGetCurrentDateUseCase.getCurrentDate()
        val location = iGetUserLocation.getUserLocation()
        return if (location.isResultSuccess()) {
            val successLocation = location as ResultState.ResultSuccess
            iGetPrayTimeUseCase.getPrayTime(
                date,
                successLocation.result?.city!!,
                successLocation.result?.country!!
            ).map {
                it.mapToPrayDTO(clock)
            }
        } else {
            val errorLocation = location as ResultState.ResultError
            flow { emit(ResultState.ResultError(errorLocation.textWrapper)) }
        }
    }
}