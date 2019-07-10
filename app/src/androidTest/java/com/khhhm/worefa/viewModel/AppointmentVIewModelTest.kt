package com.khhhm.worefa.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.utilities.getValue
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.network.AppointmentApiService
import com.khhhm.worefa.data.network.CompanyApiService
import com.khhhm.worefa.data.repository.AppointmentRepository
import com.khhhm.worefa.data.repository.CompanyRepository
import com.khhhm.worefa.viewmodels.AppointmentViewModel
import com.khhhm.worefa.viewmodels.CompanyViewModel
import junit.framework.Assert
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppointmentVIewModelTest {
    private lateinit var appDatabase: WorefaDatabase
    private lateinit var viewModel: AppointmentViewModel
    val companny1 = Appointment(4,32,5,"jjjk","dhksa",3,4)
    val companny2 = Appointment(2,31,5,"jjjk","dhksa",3,4)
    val companny3 = Appointment(3,13,5,"jjjk","dhksa",3,4)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = WorefaDatabase.getDatabase(context)
        val companyRepository: CompanyRepository = CompanyRepository.getInstance(appDatabase.companyDao(),
            CompanyApiService.getInstance())
        // viewModel = CompanyViewModel(plantRepo, company.Id)

        viewModel= AppointmentViewModel(Application())
        viewModel.insertAppointment(companny1)
        viewModel.insertAppointment(companny2)

    }


    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetAllAppointments(){
        MatcherAssert.assertThat(getValue(viewModel.getAllMyAppointments()).size, Matchers.equalTo(3))
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDeleteAppointments() {
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.deleteAppointment(companny1)
          //  MatcherAssert.assertThat(getValue(viewModel.deleteAppointment(companny1))), Matchers.equalTo(2)
        }
    }


    @Test
    @Throws(InterruptedException::class)
    fun testUdateAppointments() {
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.updateAppointment(companny1)
            //  MatcherAssert.assertThat(getValue(viewModel.deleteAppointment(companny1))), Matchers.equalTo(2)
        }
    }


    @Test
    @Throws(InterruptedException::class)
    fun testDefaultData(){
      //  Assert.assertTrue(viewModel.equals(Appointment(1, 1, 2, "", "",4,6)))
    }
}