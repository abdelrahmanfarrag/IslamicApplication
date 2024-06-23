package com.islamic.local.localdatasource.quran

import com.islamic.local.entities.LocalQuran
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class QuranLocalDataSourceShould {

    private lateinit var iQuranLocalDataSource: IQuranLocalDataSource

    @Test
    fun `save quran tafsir,audios and meta in local database`() = runTest {
        val fakeDao = FakeQuranDao()
        val testQuan = LocalQuran(id = 1)
        iQuranLocalDataSource = QuranLocalDataSource(fakeDao)
        iQuranLocalDataSource.insertQuranData(testQuan)
        assertEquals(fakeDao.mLocalQuran.isNotEmpty(), true)
    }

    @Test
    fun `return quran tafsir,audios and meta from local database`() = runTest {
        val fakeDao = FakeQuranDao()
        val testQuan = LocalQuran(id = 1)
        iQuranLocalDataSource = QuranLocalDataSource(fakeDao)
        iQuranLocalDataSource.insertQuranData(testQuan)
        assertEquals(iQuranLocalDataSource.getQuranData().first().contains(testQuan), true)
    }
}