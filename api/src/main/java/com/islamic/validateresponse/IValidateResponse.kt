package com.islamic.validateresponse

import retrofit2.Response

interface IValidateResponse {
    fun <T> validateResponse(response: Response<T>): ServerResponseState<T>
}