package com.khhhm.worefa.Dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.UserDao
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.entitys.User
import com.khhhm.worefa.utilities.getValue
import org.hamcrest.Matchers
import org.junit.*

class UserDaoTest {
    private lateinit var database: WorefaDatabase
    private lateinit var userDao: UserDao
    private val user1= User("kiber","asdasdsa")
        private val user2= User("henok","dsdsds")
    private val user3=User("habib","dsdsds")
    @Before
    fun createDb(){
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        database= WorefaDatabase.getDatabase(context)
        userDao=database.userDao()
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

    }
    @After
    fun tearOff(){
        database.close()
    }
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun testGetAll(){

        Assert.assertThat(getValue(userDao.getByPhoneNo(user1.phone_no)), Matchers.equalTo(user1))
        Assert.assertThat(getValue(userDao.getByPhoneNo(user2.phone_no)), Matchers.equalTo(user2))
        Assert.assertThat(getValue(userDao.getByPhoneNo(user3.phone_no)), Matchers.equalTo(user3))
    }
}