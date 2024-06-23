package com.example.domain.usecase

import com.example.domain.entities.QuranDTO
import com.example.domain.repository.IQuranRepository
import com.islamic.domain.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadQuranInitialDataUseCase @Inject constructor(
    private val iQuranRepository: IQuranRepository
) : ILoadQuranInitialDataUseCase {
    override suspend fun invoke(): Flow<ResultState<QuranDTO>> {
        return iQuranRepository.getQuran()
    }
}