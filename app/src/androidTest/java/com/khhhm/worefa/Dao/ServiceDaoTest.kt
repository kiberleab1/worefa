package com.khhhm.worefa.Dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.ServicesDao
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.utilities.getValue
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.*

class ServiceDaoTest {
    private lateinit var database: WorefaDatabase
    private lateinit var serviceDao: ServicesDao
    val services = listOf<Services>(
        Services(1,1,"henok","sss","img1","the kkk",2.9),
        Services(5,2,"habib","sss","img1","the kkk",2.5))
        /*Services(6,2,"habib","sss","img1","the kkk",2.5),
        Services(7,3,"kibr","sss","img1","the kkk",2.4))*/
   /* private val service1= Services(5,1,"henok","sss","img1","the kkk",2.9)
    private val service2= Services(6,2,"habib","sss","img1","the kkk",2.5)
    private val service3=Services(7,3,"kibr","sss","img1","the kkk",2.4)
*/
    @Before
    fun createDb(){
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        database= WorefaDatabase.getDatabase(context)
        serviceDao=database.servicesDao()
        this.serviceDao.insertAllService(services)


    }
    @After
    fun tearOff(){
        database.close()
    }
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun testGetAll(){
        val serviceList= getValue(serviceDao.getService())
        val a=serviceList.size
        val b=services[0].Id
        MatcherAssert.assertThat(serviceList.size, Matchers.equalTo(4))
        MatcherAssert.assertThat(serviceList[0].Id, Matchers.equalTo(services[0].Id))
        MatcherAssert.assertThat(serviceList[1].Id, Matchers.equalTo(services[1].Id))
        //MatcherAssert.assertThat(serviceList[2].Id, Matchers.equalTo(services[1].Id))

    }
}