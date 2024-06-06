package com.islamic.domain.usecase.praytimes

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.Pray
import com.islamic.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPrayTimeUseCase @Inject constructor(
    private val iHomeRepository: IHomeRepository
) : IGetPrayTimeUseCase {
    override suspend fun getPrayTime(
        date: String,
        city: String,
        country: String
    ): Flow<ResultState<Pray?>> {
        return iHomeRepository.getPrayTime(date, city, country)
    }
}