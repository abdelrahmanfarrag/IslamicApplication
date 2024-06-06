package com.islamic.domain.usecase.home

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.PrayDTO
import kotlinx.coroutines.flow.Flow

interface ILoadPrayForHomeUseCase {
    suspend fun getPrayDTO(
    ): Flow<ResultState<PrayDTO>>
}