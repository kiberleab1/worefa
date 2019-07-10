package com.khhhm.worefa.Dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.utilities.getValue
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.AppointmentDao
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.dao.ServicesDao
import com.khhhm.worefa.data.dao.UserDao
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.data.entitys.User
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.After


@RunWith(AndroidJUnit4::class)
class AppointmentDaoTest {
    private lateinit var database: WorefaDatabase
    private lateinit var appointmentDao: AppointmentDao
    private lateinit var userDao:UserDao
    private lateinit var serviceDao:ServicesDao
    private lateinit var companyDao: CompanyDao
    /*private val appoint1= Appointment(1,1,1,"keber","6/6/6",1,12)
    private val appoint2= Appointment(2,2,1,"keber","6/6/6",1,12)
    private val appoint3= Appointment(3,3,1,"keber","6/6/6",1,12)
   */
    val appointments = listOf<Appointment>(
        Appointment(1,1,1,"keber","6/6/6",1,12),
        Appointment(2,2,1,"keber","6/6/6",1,12),
        Appointment(3,3,1,"keber","6/6/6",1,12))

    @Before
    fun createDb(){
        val context:Context=InstrumentationRegistry.getInstrumentation().targetContext
        database=WorefaDatabase.getDatabase(context)
        appointmentDao=database.appointmentDao()
        userDao=database.userDao()
        serviceDao=database.servicesDao()
        companyDao=database.companyDao()
        companyDao.insertAll(listOf(
            Company(1,"","","","")
        ))
        serviceDao.insertService(Services(3,4,"henok","kk","lll","cccc",2.1))
            userDao.insertUser(User("keber","asdd"))
        appointmentDao.insertAll(appointments)
      }
    @After
    fun tearOff(){
        database.close()
    }
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun testGetAll(){
        val appoinmentList= getValue(appointmentDao.getAllappointments())
        val a=appoinmentList.size
        val b=appointments[0].id
        assertThat(appoinmentList.size, Matchers.equalTo(appointments.size))
        assertThat(appoinmentList[0].id , Matchers.equalTo(appointments[0].id))
        assertThat(appoinmentList[1].id,Matchers.equalTo(appointments[1].id))
        assertThat(appoinmentList[1].service_id,Matchers.equalTo(appointments[1].service_id))
    }
}