package com.feature_surrah.data.repository

import com.feature_surrah.data.mapper.mapToSurrah
import com.feature_surrah.domain.models.Surrah
import com.feature_surrah.domain.repository.ISurrahRepository
import com.islamic.core_domain.R
import com.islamic.data.utils.mapToResultState
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SurrahRepository @Inject constructor(
    private val iQuranRemoteDataSource: IQuranRemoteDataSource
) : ISurrahRepository {
    override suspend fun getSurrah(
        number: Int,
        selections: ArrayList<String>
    ): Flow<ResultState<out Surrah?>> {
        val selectionPath = createSelectionsPath(selections)
        val result = iQuranRemoteDataSource.getQuranData(1, selectionPath).mapToResultState {
            val surrah = it?.data?.mapToSurrah()
            return@mapToResultState it?.data?.mapToSurrah()
        }
        val surrahFlow = flow { emit(result) }
            .map {
                return@map if (it is ResultState.ResultSuccess) {
                    if (it.result == null)
                        ResultState.ResultError<Surrah>(TextWrapper.ResourceText(R.string.something_went_wrong))
                    else
                        it
                } else
                    it
            }
        return surrahFlow
    }

    private fun createSelectionsPath(selections: ArrayList<String>): String {
        return selections.reduce { result, nr -> "$result,$nr" }
    }
}