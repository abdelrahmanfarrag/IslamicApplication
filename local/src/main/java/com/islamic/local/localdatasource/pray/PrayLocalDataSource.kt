package com.islamic.local.localdatasource.pray

import com.islamic.local.database.dao.PrayDao
import com.islamic.local.entities.CachedPray
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrayLocalDataSource @Inject constructor(
    private val prayDao: PrayDao
) : IPrayLocalDataSource {
    override suspend fun upsertPray(cachedPray: CachedPray) {
        prayDao.upsertPray(cachedPray)
    }

    override suspend fun clearDatabase() {
        prayDao.clearDatabase()
    }

    override  fun getPray(): Flow<List<CachedPray>> {
       return prayDao.getPray()
    }
}