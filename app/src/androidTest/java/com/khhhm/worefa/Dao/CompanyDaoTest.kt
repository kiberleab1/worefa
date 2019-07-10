package com.khhhm.worefa.Dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.CompanyDao
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.utilities.getValue
import org.hamcrest.Matchers
import org.junit.*

class CompanyDaoTest {
    private lateinit var database: WorefaDatabase
    private lateinit var companyDao: CompanyDao

     val companies = listOf<Company>(
        Company(1,"as","ds","sds","sdsdsd"),
        Company(2,"as","ds","sds","sdsdsd"),
        Company(3,"as","ds","sds","sdsdsd")
        )
    @Before
    fun createDb(){
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        database= WorefaDatabase.getDatabase(context)
        companyDao=database.companyDao()
        companyDao.insertAll(companies)
     }
    @After
    fun tearOff(){
        database.close()
    }
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun testGetAll(){
        val companyList= getValue(companyDao.getAllCompanys())
        Assert.assertThat(companies.size, Matchers.equalTo(3))
        Assert.assertThat(companies.get(0).Id, Matchers.equalTo(1))
        Assert.assertThat(companies.get(1).Id, Matchers.equalTo(2))
        Assert.assertThat(companies.get(2).Id, Matchers.equalTo(3))
    }
}