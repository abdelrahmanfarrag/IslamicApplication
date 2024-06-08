package com.islamic.local.localdatasource.pray

import com.islamic.local.database.PrayDao
import com.islamic.local.entities.CachedPray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePrayDao : PrayDao {
     val cachedPrayList = arrayListOf<CachedPray>()
    override suspend fun upsertPray(cachedPray: CachedPray) {
        cachedPrayList.clear()
        cachedPrayList.add(cachedPray)
    }

    override suspend fun clearDatabase() {
        cachedPrayList.clear()
    }

    override  fun getPray(): Flow<List<CachedPray>> {
        return flow {
            emit(cachedPrayList)
        }
    }
}