package com.feature_surrah.domain.usecase

import com.feature_surrah.domain.models.Surrah
import com.islamic.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface IGetSurrahUseCase {
    suspend fun getSurrah(number: Int, selections: ArrayList<String>): Flow<ResultState<Surrah>>
}