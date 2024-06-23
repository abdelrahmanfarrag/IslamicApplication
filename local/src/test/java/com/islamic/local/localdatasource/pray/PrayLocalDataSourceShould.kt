package com.islamic.local.localdatasource.pray

import com.islamic.local.entities.CachedPray
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PrayLocalDataSourceShould {

    private lateinit var iPrayLocalDataSource: IPrayLocalDataSource

    @Test
    fun `insert one item successfully`() = runTest {
        val fakePrayDao = FakePrayDao()
        iPrayLocalDataSource = PrayLocalDataSource(fakePrayDao)
        iPrayLocalDataSource.upsertPray(CachedPray(id = 1))
        assertEquals(true, fakePrayDao.cachedPrayList.find {
            it.id == 1
        } != null)
        assertEquals(true, fakePrayDao.cachedPrayList.isNotEmpty())
    }

    @Test
    fun `clear database successfully`()= runTest {
        val fakePrayDao = FakePrayDao()
        iPrayLocalDataSource = PrayLocalDataSource(fakePrayDao)
        iPrayLocalDataSource.clearDatabase()
        assertEquals(true, fakePrayDao.cachedPrayList.isEmpty())
    }

    @Test
    fun `return saved object successfully`()= runTest {
        val fakePrayDao = FakePrayDao()
        iPrayLocalDataSource = PrayLocalDataSource(fakePrayDao)
        val cachedPray = CachedPray()
        iPrayLocalDataSource.upsertPray(cachedPray)
        iPrayLocalDataSource.getPray()
        assertEquals(true, fakePrayDao.cachedPrayList.contains(cachedPray))
    }

}