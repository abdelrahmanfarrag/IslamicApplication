package com.islamic.domain

sealed class ResultState<T> {
    data class ResultSuccess<T>(val result: T?) : ResultState<T>()
    data class ResultError(val textWrapper: TextWrapper) : ResultState<TextWrapper>()
}