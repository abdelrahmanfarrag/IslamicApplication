package com.islamic.domain

sealed class ResultState<T> {
    data class ResultSuccess<T>(val result: T?) : ResultState<T>()
    data class ResultError<T>(val textWrapper: TextWrapper) : ResultState<T>()


    fun isResultSuccess(): Boolean {
        return this is ResultSuccess
    }

    fun ResultSuccess<T>.onSuccess(action: (T?) -> Unit): ResultState<T> {
        action.invoke(this.result)
        return this@ResultState
    }

    fun ResultError<T>.onError(action : (TextWrapper)->Unit){
        action.invoke(this.textWrapper)
    }
}