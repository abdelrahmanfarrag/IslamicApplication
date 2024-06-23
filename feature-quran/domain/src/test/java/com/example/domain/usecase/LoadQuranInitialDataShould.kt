package com.example.domain.usecase

import com.example.domain.entities.QuranDTO
import com.example.domain.repository.IQuranRepository
import com.islamic.domain.ResultState
import com.islamic.domain.TextWrapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoadQuranInitialDataShould {

    private lateinit var iLoadQuranInitialDataUseCase: ILoadQuranInitialDataUseCase
    private val iQuranRepository = mock<IQuranRepository>()


    @Test
    fun `return success in case of result state is success`() = runTest {
        val dto = QuranDTO()
        whenever(iQuranRepository.getQuran()).thenReturn(
            flow {
                emit(ResultState.ResultSuccess(dto))
            }
        )
        iLoadQuranInitialDataUseCase = LoadQuranInitialDataUseCase(iQuranRepository)
        val result = iLoadQuranInitialDataUseCase().first()
        assertEquals(ResultState.ResultSuccess(dto), result)
    }

    @Test
    fun `return error in case of result state is error`() = runTest {
        val text = TextWrapper.StringText("Error")
        whenever(iQuranRepository.getQuran()).thenReturn(
            flow {
                emit(ResultState.ResultError(text))
            }
        )
        iLoadQuranInitialDataUseCase = LoadQuranInitialDataUseCase(iQuranRepository)
        val result = iLoadQuranInitialDataUseCase().first()
        assertEquals(ResultState.ResultError<QuranDTO>(text), result)
    }

}