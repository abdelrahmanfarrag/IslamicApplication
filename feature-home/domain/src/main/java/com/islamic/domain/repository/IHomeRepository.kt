package com.islamic.domain.repository

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.Pray
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

   suspend fun clearRecords()
   suspend fun getPrayTime(data: String, city: String, country: String): Flow<ResultState<Pray?>>
}