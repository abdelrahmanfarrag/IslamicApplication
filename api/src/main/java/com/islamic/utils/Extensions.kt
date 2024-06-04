package com.islamic.utils

import com.islamic.validateresponse.ServerResponseState


fun <T> ServerResponseState<T>.mapBasedOnNetworkState(isNetworkAvailable: Boolean): ServerResponseState<T> {
    return if (isNetworkAvailable)
        this
    else
        ServerResponseState.NoInternetConnection as ServerResponseState<T>
}