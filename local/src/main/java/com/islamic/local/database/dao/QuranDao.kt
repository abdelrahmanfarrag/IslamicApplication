package com.islamic.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.islamic.local.entities.LocalQuran
import kotlinx.coroutines.flow.Flow

@Dao
interface QuranDao {
    @Upsert
    fun insertQuranData(localQuran: LocalQuran)

    @Query("SELECT * from LocalQuran LIMIT 1")
    fun getLocalQuranData(): Flow<List<LocalQuran>>

}