package com.islamic.api.validateresponse

import com.islamic.validateresponse.IValidateResponse
import com.islamic.validateresponse.ServerResponseState
import com.islamic.validateresponse.ValidateResponse
import kotlinx.coroutines.test.runTest
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class ValidateResponseShould {

    private lateinit var iValidateResponse: IValidateResponse
    private lateinit var responseHelper:ResponseHelper

    private fun initTests() {
        iValidateResponse = ValidateResponse()
        responseHelper= ResponseHelper()
    }

    @Test
    fun `return success when code = 200 and response is successful and response body is not null`() = runTest {
        initTests()
        val mockedResponse = mock<Any>()
        val response = iValidateResponse.validateResponse(responseHelper.successResponse(mockedResponse))
        assertEquals(response, ServerResponseState.StateSuccess(mockedResponse))
    }

    @Test
    fun `return error when response body is not null `()= runTest {
        initTests()
        val response = iValidateResponse.validateResponse(responseHelper.nullResponse())
        assertEquals(response, ServerResponseState.StateError<Any>(200))
    }
    @Test
    fun `return error when status code is not 200`()= runTest {
        initTests()
        val response = iValidateResponse.validateResponse(responseHelper.emptyErrorResponse(600))
        assertEquals(response, ServerResponseState.StateError<Any>(600))
    }

    @Test
    fun `return error when api call isSuccessful false`()= runTest {
        initTests()
        val mockedResponse = mock<Response<*>>()
        whenever(mockedResponse.isSuccessful).thenReturn(false)
        val response = iValidateResponse.validateResponse(mockedResponse)
        assertEquals(response, ServerResponseState.StateError<Any>(mockedResponse.code()))
    }

    @Test
    fun `return error when exception is thrown`()= runTest{
        initTests()
        val mock = mock<Response<*>>()
        whenever(mock.body()).thenThrow(RuntimeException("error"))
        val response = iValidateResponse.validateResponse(mock)
        assertEquals(response, ServerResponseState.StateError<Any>(mock.code()))
    }
}