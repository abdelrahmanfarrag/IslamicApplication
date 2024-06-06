package com.islamic.domain.usecase.getpraytime

import com.islamic.domain.ResultState
import com.islamic.domain.mapper.Pray
import com.islamic.domain.repository.IHomeRepository
import com.islamic.domain.usecase.praytimes.GetPrayTimeUseCase
import com.islamic.domain.usecase.praytimes.IGetPrayTimeUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPrayTimeUseCaseShould {

    private lateinit var getPrayTimeUseCase: IGetPrayTimeUseCase
    private val homeIHomeRepository = mock<IHomeRepository>()
    @Test
    fun `get pray times from repository with DataState is being collected`() = runTest {
        whenever(homeIHomeRepository.getPrayTime("", "", "")).thenReturn(flow {
            emit(ResultState.ResultSuccess(Pray()))
        })
        getPrayTimeUseCase =
            GetPrayTimeUseCase(homeIHomeRepository)
        val result = getPrayTimeUseCase.getPrayTime("", "", "").first()
        assertEquals(ResultState.ResultSuccess(Pray()), result)
    }
}