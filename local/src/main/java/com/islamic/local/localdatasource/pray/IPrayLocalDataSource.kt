package com.islamic.local.localdatasource.pray

import com.islamic.local.entities.CachedPray
import kotlinx.coroutines.flow.Flow

interface IPrayLocalDataSource {
    suspend fun upsertPray(cachedPray: CachedPray)

    suspend fun clearDatabase()
    fun getPray(): Flow<List<CachedPray>>
}