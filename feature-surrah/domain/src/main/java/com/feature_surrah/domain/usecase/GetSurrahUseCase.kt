package com.feature_surrah.domain.usecase

import com.feature_surrah.domain.models.Surrah
import com.feature_surrah.domain.repository.ISurrahRepository
import com.islamic.domain.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSurrahUseCase @Inject constructor(
    private val iSurrahRepository: ISurrahRepository
) : IGetSurrahUseCase {
    override suspend fun getSurrah(
        number: Int,
        selections: ArrayList<String>
    ): Flow<ResultState<Surrah>> {
        return iSurrahRepository.getSurrah(number, selections)
    }
}