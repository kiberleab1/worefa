package com.khhhm.worefa.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.khhhm.worefa.data.entitys.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest{
    private lateinit var database: WorefaDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
       database = Room.databaseBuilder(
           ApplicationProvider.getApplicationContext(),
           WorefaDatabase::class.java,"WorefaDatabase").allowMainThreadQueries().build()

    }

    @After
    fun closeDb() = database.close()


    @Test
    fun insertGroupAndGetByEmail() = runBlocking {
        // GIVEN - insert a group
        val user = User("henok","addis","meles","henokaddis@gmail.com","0938948909","1234")
        database.userDao().insertUser(user)

        // WHEN - Get the group by code from the database
        val loaded = database.userDao().getByEmail(user.email)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<User>(loaded as User, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.email, CoreMatchers.`is`(user.email))
        MatcherAssert.assertThat(loaded.fname, CoreMatchers.`is`(user.fname))
        MatcherAssert.assertThat(loaded.lname, CoreMatchers.`is`(user.lname))
        MatcherAssert.assertThat(loaded.mname, CoreMatchers.`is`(user.mname))
    }


    @Test
    fun deleteUserAndGet() = runBlockingTest {
        // Given a group inserted
        val user = User("B", "abebe","man" ,"Appraiser","0988766555","84949475")

        database.userDao().DeleteUser(user.email)

        // When deleting a task by id
        database.userDao().DeleteUser(user.email)

        // THEN - The object returned is null
        val getUser = database.userDao().getByEmail(user.email)
        MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
    }


    @Test
    fun getAllUsers() = runBlockingTest {
        val user = User("B", "surafel","fff" ,"heheh@gmail.com","09286283","1234")
        database.userDao().insertUser(user)

        val result = database.userDao().getByEmail(user.email)

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}

