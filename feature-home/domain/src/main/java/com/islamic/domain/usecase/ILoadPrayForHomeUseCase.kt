package com.islamic.domain.usecase

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.PrayDTO
import kotlinx.coroutines.flow.Flow
import java.time.Clock

interface ILoadPrayForHomeUseCase {
    suspend fun getPrayDTO(
        date: String,
        city: String,
        country: String,
    ): Flow<ResultState<PrayDTO>>
}