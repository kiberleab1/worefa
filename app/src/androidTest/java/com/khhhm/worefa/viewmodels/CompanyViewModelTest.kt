package com.khhhm.worefa.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khhhm.worefa.data.WorefaDatabase
import org.junit.Rule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.utilites.getValue
import com.khhhm.worefa.data.repository.CompanyRepository
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import com.khhhm.worefa.utilites.getValue as getValue1


class CompanyViewModelTest {
    private lateinit var database: WorefaDatabase
    private lateinit var companyViewModel: CompanyViewModel
    private lateinit var companyApiService: CompanyApiService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, WorefaDatabase::class.java).build()

        val companyRepository = CompanyRepository.getInstance(database.companyDao(),companyApiService)
        companyViewModel = CompanyViewModel(companyRepository.getAllCompany())

        @After
        fun tearDown() {
            database.close()
        }

        @Test
        @Throws(InterruptedException::class)
        fun testDefaultValues() {
            assertFalse(getValue(companyViewModel.getCompanys()?.value()))
        }
    }

    }

