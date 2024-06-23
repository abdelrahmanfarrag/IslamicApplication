package com.example.data.repository

import com.example.data.mapper.mapToLocalQuran
import com.example.data.mapper.mapToQuranDTO
import com.example.data.mapper.mapToQuranMeta
import com.example.data.mapper.mapToQuranSheikhAudio
import com.example.data.mapper.mapToQuranTafsir
import com.example.domain.entities.QuranDTO
import com.example.domain.repository.IQuranRepository
import com.google.gson.Gson
import com.islamic.data.utils.mapToResultState
import com.islamic.domain.ResultState
import com.islamic.local.localdatasource.quran.IQuranLocalDataSource
import com.islamic.remotedatasource.quran.IQuranRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class QuranRepository @Inject constructor(
    private val iQuranRemoteDataSource: IQuranRemoteDataSource,
    private val iQuranLocalDataSource: IQuranLocalDataSource,
    private val mGson: Gson
) : IQuranRepository {
    override suspend fun getQuran(): Flow<ResultState<QuranDTO>> {
        val localQuran = iQuranLocalDataSource.getQuranData()
        return localQuran.map { localQuranResult ->
            return@map if (localQuranResult.isEmpty()) {
                val joinNetworkCalls = joinQuranNetworkCalls()
                if (joinNetworkCalls is ResultState.ResultSuccess) {
                    joinNetworkCalls.result?.mapToLocalQuran(mGson)?.let {
                        iQuranLocalDataSource.insertQuranData(it)
                    }
                    joinNetworkCalls
                } else
                    joinNetworkCalls
            } else {
                val cachedLocalQuran = iQuranLocalDataSource.getQuranData().first().map {
                    it.mapToQuranDTO(mGson)
                }
                ResultState.ResultSuccess(result = cachedLocalQuran[0])
            }
        }
    }

    private suspend fun joinQuranNetworkCalls(): ResultState<QuranDTO> {
        var quranDTO = QuranDTO()
        val quranMetaCall = flow {
            emit(iQuranRemoteDataSource.getQuranMeta().mapToResultState {
                it?.data?.mapToQuranMeta()
            })
        }
        val quranSheikhAudioCall =
            flow {
                emit(iQuranRemoteDataSource.getQuranAvailableSheikhAudios().mapToResultState {
                    it?.data?.map { dataResult ->
                        return@map dataResult.mapToQuranSheikhAudio()
                    }
                })
            }
        val quranTafsirCall = flow {
            emit(iQuranRemoteDataSource.getQuranAvailableTafsirTypes().mapToResultState {
                it?.data?.map { dataResult ->
                    return@map dataResult.mapToQuranTafsir()
                }
            })
        }
        return quranMetaCall.zip(quranSheikhAudioCall) { meta, audio ->
            if (meta is ResultState.ResultError)
                ResultState.ResultError(textWrapper = meta.textWrapper)
            else if (audio is ResultState.ResultError)
                ResultState.ResultError(textWrapper = audio.textWrapper)
            else {
                quranDTO = quranDTO.copy(
                    quranMeta = (meta as ResultState.ResultSuccess).result,
                    quranSheikhAudios = (audio as ResultState.ResultSuccess).result
                )
                ResultState.ResultSuccess(
                    result = quranDTO
                )
            }

        }.zip(quranTafsirCall) { firstZipResult, tafsir ->
            if (firstZipResult is ResultState.ResultError)
                ResultState.ResultError(textWrapper = firstZipResult.textWrapper)
            else if (tafsir is ResultState.ResultError)
                ResultState.ResultError(textWrapper = tafsir.textWrapper)
            else {
                quranDTO = quranDTO.copy(
                    quranTafsir = (tafsir as ResultState.ResultSuccess).result
                )
                ResultState.ResultSuccess(result = quranDTO)
            }
        }.first()
    }
}