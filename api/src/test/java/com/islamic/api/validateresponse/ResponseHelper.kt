package com.islamic.api.validateresponse

import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class ResponseHelper {
    fun <T> successResponse(t: T): Response<T> {
        return Response.success(t)
    }

    fun nullResponse(): Response<*> {
    val response = Response.success(null)
        return response
    }

    fun emptyErrorResponse(code:Int): Response<Any> {
        return Response.error(code, "".toResponseBody())
    }
}