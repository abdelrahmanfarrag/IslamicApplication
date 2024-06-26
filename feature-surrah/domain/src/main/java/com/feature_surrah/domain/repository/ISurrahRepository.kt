package com.feature_surrah.domain.repository

import com.feature_surrah.domain.models.Surrah
import com.islamic.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface ISurrahRepository {
    suspend fun getSurrah(number: Int, selections: ArrayList<String>):Flow<ResultState<out Surrah?>>
}