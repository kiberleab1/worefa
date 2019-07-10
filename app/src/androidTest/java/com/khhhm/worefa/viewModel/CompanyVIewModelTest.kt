package com.khhhm.worefa.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.utilities.getValue
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.data.repository.CompanyRepository
import com.khhhm.worefa.viewmodels.CompanyViewModel
import junit.framework.Assert.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CompanyVIewModelTest {
    private lateinit var appDatabase: WorefaDatabase
    private lateinit var viewModel: CompanyViewModel
    val companny1 = Company(4,"henok","hhh","jjjk","dhksa")
    val companny2 = Company(4,"henok","hhh","jjjk","dhksa")
    val companny3 = Company(4,"henok","hhh","jjjk","dhksa")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = WorefaDatabase.getDatabase(context)
        val companyRepository:CompanyRepository= CompanyRepository.getInstance(appDatabase.companyDao(),
            CompanyApiService.getInstance())
       // viewModel = CompanyViewModel(plantRepo, company.Id)

        viewModel= CompanyViewModel(Application())
        viewModel.insertCompany(companny1)
        viewModel.insertCompany(companny2)


    }


    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testCompanys(){
        assertThat(getValue(viewModel.getAllCompanys()).size,equalTo(3))
     }
    @Test
    @Throws(InterruptedException::class)
    fun testDefaultData(){
        assertTrue(viewModel.newCompany.equals(Company(1,"","","","")))
    }
}