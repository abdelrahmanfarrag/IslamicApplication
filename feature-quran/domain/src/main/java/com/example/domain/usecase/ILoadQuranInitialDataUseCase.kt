package com.example.domain.usecase

import com.example.domain.entities.QuranDTO
import com.islamic.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface ILoadQuranInitialDataUseCase {
    suspend operator fun invoke(): Flow<ResultState<QuranDTO>>
}