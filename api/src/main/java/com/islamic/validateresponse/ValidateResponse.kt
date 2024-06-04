package com.islamic.validateresponse

import com.islamic.validateresponse.Constants.SUCCESS_CODE
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class ValidateResponse : IValidateResponse {
    override fun <T> validateResponse(response: Response<T>): ServerResponseState<T> {
        val code = response.code()
        val isSuccessful = response.isSuccessful
        return try {
            if (isSuccessful && code == SUCCESS_CODE) {
                val body = response.body()
                if (body != null)
                    ServerResponseState.StateSuccess(body)
                else
                    ServerResponseState.StateError(code) as ServerResponseState<T>
            } else
                ServerResponseState.StateError(code) as ServerResponseState<T>
        } catch (e: Exception) {
            ServerResponseState.StateError(code) as ServerResponseState<T>
        }
    }
}