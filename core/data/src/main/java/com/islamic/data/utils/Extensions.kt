@file:Suppress("UNCHECKED_CAST")

package com.islamic.data.utils

import com.islamic.core_domain.R
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import com.islamic.validateresponse.ServerResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun <T, D> ServerResponseState<T>.mapToResultState(mapResult: (T?) -> D): Flow<ResultState<D>> {
    val resultState = when (this) {
        is ServerResponseState.StateSuccess<T> -> ResultState.ResultSuccess(mapResult(this.data))
        is ServerResponseState.StateError -> ResultState.ResultError(TextWrapper.ResourceText(R.string.something_went_wrong))
        ServerResponseState.NoInternetConnection -> ResultState.ResultError(
            TextWrapper.ResourceText(
                R.string.no_internet
            )
        )
    }
    return flow {
        emit(resultState as ResultState<D>)
    }
}