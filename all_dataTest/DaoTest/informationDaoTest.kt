package com.khhhm.worefa.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.khhhm.worefa.data.entitys.Information
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
class informationDaoTest{
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
    fun insertGroupAndGetByCode() = runBlocking {
        // GIVEN - insert a group
        val information = Information(1,2,"meles","henokaddis@gmail.com","0938948909")
        database.informationDao().insertInformation(information)

        // WHEN - Get the group by code from the database
        val loaded = database.informationDao().getSingleInformation(information.Id.toLong())

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Information>(loaded as Information, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.Id, CoreMatchers.`is`(information.Id))
        MatcherAssert.assertThat(loaded.companyID, CoreMatchers.`is`(information.companyID))
        MatcherAssert.assertThat(loaded.detail, CoreMatchers.`is`(information.detail))
        MatcherAssert.assertThat(loaded.filePath, CoreMatchers.`is`(information.filePath))
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
        val information = Information(2, 4,"fff" ,"heheh@gmail.com","09286283")
        database.informationDao().insertInformation(information)

        val result = database.informationDao().getSingleInformation(information.Id.toLong())

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}
