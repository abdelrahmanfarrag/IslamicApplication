package com.islamic.validateresponse

sealed class ServerResponseState<T>(val response: T?) {
    data object NoInternetConnection : ServerResponseState<Any>(null)
    data class StateError<T>(val errorCode: Int?) : ServerResponseState<Any>(errorCode)
    data class StateSuccess<T>(val data: T?) : ServerResponseState<T>(data)

}