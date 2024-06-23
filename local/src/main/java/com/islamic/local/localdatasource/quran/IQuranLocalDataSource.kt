package com.islamic.local.localdatasource.quran

import com.islamic.local.entities.LocalQuran
import kotlinx.coroutines.flow.Flow

interface IQuranLocalDataSource {

  suspend  fun insertQuranData(localQuran: LocalQuran)

    fun getQuranData(): Flow<List<LocalQuran>>

}