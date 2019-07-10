package com.khhhm.worefa.RepositoryTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.utilities.getValue
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.data.network.ServiceApiService
import com.khhhm.worefa.data.repository.CompanyRepository
import com.khhhm.worefa.data.repository.ServicesRepository
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

class ServicesRepoTest {
    private lateinit var appDatabase: WorefaDatabase
    private lateinit var servicesRepository: ServicesRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = WorefaDatabase.getDatabase(context)
        servicesRepository = ServicesRepository.getInstance(appDatabase.servicesDao(),
            ServiceApiService.getInstance())
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
            assertThat(getValue(servicesRepository.getAll()).size, equalTo(2))
       }

    @Test
    @Throws(InterruptedException::class)
    fun testInsertCompanys(){
        GlobalScope.launch(Dispatchers.IO) {
        assertEquals(servicesRepository.insertService(Services(1,4,"henok","hhhh","ddd","nnc",1.2)),Unit)
    }}
}