package com.islamic.domain

sealed class ResultState<T> {
    data class ResultSuccess<T>(val result: T?) : ResultState<T>()
    data class ResultError<T>(val textWrapper: TextWrapper) : ResultState<T>()


    fun isResultSuccess():Boolean{
        return this is ResultSuccess
    }
}