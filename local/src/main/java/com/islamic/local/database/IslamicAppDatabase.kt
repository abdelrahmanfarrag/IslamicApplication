package com.islamic.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.islamic.local.entities.CachedPray

@Database(entities = [CachedPray::class], version = 1)
abstract class IslamicAppDatabase : RoomDatabase() {

    abstract val prayDao: PrayDao
}