package com.khhhm.worefa
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.khhhm.worefa.data.WorefaDatabase
import com.khhhm.worefa.data.dao.UserDao
import com.khhhm.worefa.data.entitys.User
import com.khhhm.worefa.utilites.getValue
import org.hamcrest.Matchers
import org.junit.*

class UserDaoTest {
    private lateinit var database: WorefaDatabase
    private lateinit var userDao: UserDao
    private val userA = User("0911", "12345")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, WorefaDatabase::class.java).build()
        userDao = database.userDao()
        userDao.insertUser(userA)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetPlants() {
        val plantList = getValue(userDao.getByPhoneNo(userA.phone_no))
        Assert.assertThat(plantList, Matchers.equalTo(userA))
    }

}