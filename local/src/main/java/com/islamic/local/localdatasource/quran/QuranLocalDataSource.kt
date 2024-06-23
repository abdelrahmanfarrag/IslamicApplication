package com.islamic.local.localdatasource.quran

import com.islamic.local.database.dao.QuranDao
import com.islamic.local.entities.LocalQuran
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuranLocalDataSource @Inject constructor(
    private val quranDao: QuranDao
) : IQuranLocalDataSource {
    override suspend fun insertQuranData(localQuran: LocalQuran) {
        quranDao.insertQuranData(localQuran)
    }

    override fun getQuranData(): Flow<List<LocalQuran>> {
        return quranDao.getLocalQuranData()
    }
}