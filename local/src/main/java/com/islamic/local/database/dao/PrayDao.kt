package com.islamic.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.islamic.local.entities.CachedPray
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayDao {
    @Upsert
    suspend fun upsertPray(cachedPray: CachedPray)

    @Query("DELETE FROM CachedPray")
    suspend fun clearDatabase()

    @Query("SELECT * from CachedPray LIMIT 1")
    fun getPray(): Flow<List<CachedPray>>
}