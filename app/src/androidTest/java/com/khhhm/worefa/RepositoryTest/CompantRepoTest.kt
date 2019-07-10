package com.khhhm.worefa.RepositoryTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.utilities.getValue
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.data.repository.CompanyRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CompantRepoTest {
    private lateinit var appDatabase: WorefaDatabase
    private lateinit var companyRepository: CompanyRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = WorefaDatabase.getDatabase(context)
         companyRepository = CompanyRepository.getInstance(appDatabase.companyDao(),
            CompanyApiService.getInstance())
        // viewModel = CompanyViewModel(plantRepo, company.Id)
        //viewModel= CompanyViewModel(Application())

    }


    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)

    fun testCompanys(){
            assertThat(getValue(companyRepository.getAllCompanyFromDao()).size, equalTo(3))
       }

    @Test
    @Throws(InterruptedException::class)
    fun testInsertCompanys(){
        GlobalScope.launch(Dispatchers.IO) {
        assertEquals(companyRepository.insertCompany(Company(1,"","","","")),Unit)
    }}
}