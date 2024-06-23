package com.example.domain.repository

import com.example.domain.entities.QuranDTO
import com.islamic.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    suspend fun getQuran(): Flow<ResultState<QuranDTO>>
}