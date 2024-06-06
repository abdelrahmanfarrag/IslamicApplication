package com.islamic.domain.usecase

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.PrayDTO
import com.islamic.domain.mapper.mapToPrayDTO
import com.islamic.domain.usecase.praytimes.IGetPrayTimeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Clock
import javax.inject.Inject

class LoadPrayForHomeUseCase @Inject constructor(
    private val iGetPrayTimeUseCase: IGetPrayTimeUseCase,
    private val clock: Clock
) : ILoadPrayForHomeUseCase {
    override suspend fun getPrayDTO(
        date: String,
        city: String,
        country: String,
    ): Flow<ResultState<PrayDTO>> {
        return iGetPrayTimeUseCase.getPrayTime(date, city, country).map {
            it.mapToPrayDTO(clock)
        }
    }
}