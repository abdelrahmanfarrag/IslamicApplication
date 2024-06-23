package com.islamic.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.islamic.local.database.dao.PrayDao
import com.islamic.local.database.dao.QuranDao
import com.islamic.local.entities.CachedPray
import com.islamic.local.entities.LocalQuran

@Database(entities = [CachedPray::class, LocalQuran::class], version = 2)
abstract class IslamicAppDatabase : RoomDatabase() {

    abstract val prayDao: PrayDao
    abstract val quranDao: QuranDao
}