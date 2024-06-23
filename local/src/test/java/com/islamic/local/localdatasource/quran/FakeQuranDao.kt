package com.islamic.local.localdatasource.quran

import com.islamic.local.database.dao.QuranDao
import com.islamic.local.entities.LocalQuran
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeQuranDao : QuranDao {
    val mLocalQuran = mutableListOf<LocalQuran>()
    override fun insertQuranData(localQuran: LocalQuran) {
        mLocalQuran.add(localQuran)
    }

    override fun getLocalQuranData(): Flow<List<LocalQuran>> {
        return flow {
            emit(mLocalQuran)
        }
    }
}