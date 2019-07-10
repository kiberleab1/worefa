package com.khhhm.worefa.RepositoryTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.utilities.getValue
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.network.AppointmentApiService
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.data.repository.AppointmentRepository
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

class AppointmentRepoTest {
    private lateinit var appDatabase: WorefaDatabase
    private lateinit var appointmentRepository: AppointmentRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = WorefaDatabase.getDatabase(context)
         appointmentRepository = AppointmentRepository.getInstance(appDatabase.appointmentDao(),
            AppointmentApiService.getInstance())
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
           // assertThat(getValue(appointmentRepository.getAllMyAppointment()).size,equals(2))
       }

    @Test
    @Throws(InterruptedException::class)
    fun testInsertCompanys(){
        GlobalScope.launch(Dispatchers.IO) {
        assertEquals(appointmentRepository.insertAppointment(Appointment(1,2,4,"henok","2/3/4/",3,6)),Unit)
    }}
}