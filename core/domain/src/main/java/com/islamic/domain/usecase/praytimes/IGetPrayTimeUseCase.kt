package com.islamic.domain.usecase.praytimes

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.Pray
import kotlinx.coroutines.flow.Flow

interface IGetPrayTimeUseCase {
   suspend fun getPrayTime(date: String, city: String, country: String): Flow<ResultState<Pray?>>
}