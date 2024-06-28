package com.feature_surrah.domain.usecase

import com.feature_surrah.domain.models.Surrah
import com.feature_surrah.domain.repository.ISurrahRepository
import com.islamic.domain.ResultState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetSurrahUseCaseShould {
    private lateinit var iGetSurrahUseCase: IGetSurrahUseCase
    private val iSurrahRepository = mock<ISurrahRepository>()

    @Test
    fun `return the result state obtained from repository`()= runTest {
        val successResult =ResultState.ResultSuccess(Surrah())
        whenever(iSurrahRepository.getSurrah(1, arrayListOf())).thenReturn(flow {
            emit(successResult)
        })
        iGetSurrahUseCase = GetSurrahUseCase(iSurrahRepository)
        val result = iGetSurrahUseCase.getSurrah(1, arrayListOf()).first()
        assertEquals(ResultState.ResultSuccess(Surrah()),result)
    }
}