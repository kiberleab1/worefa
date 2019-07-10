package com.khhhm.worefa.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.repository.InformationRepostitory
import com.khhhm.worefa.viewmodels.InformationViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InfoVIewModelTest {
    private lateinit var database: WorefaDatabase
    private lateinit var viewModel: InformationViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, WorefaDatabase::class.java).build()

        val plantRepo = InformationRepostitory.getInstance(database.informationDao())

       // viewModel = viewModel(plantRepo, Information.)
    }


    @After
    fun tearDown() {
        database.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() {
       // assertFalse(getValue(viewModel.))
    }
}