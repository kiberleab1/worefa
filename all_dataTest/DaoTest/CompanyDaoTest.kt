package com.khhhm.worefa.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.data.entitys.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class CompanyDaoTest{
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
    fun insertCompanyAndGet() = runBlockingTest {
        // GIVEN - insert a group
        val company = Company(1,"addis","meles","henokaddis@gmail.com")
        database.companyDao().insertAll(company)

        // WHEN - Get the group by code from the database
        val loaded = database.companyDao().getCompany(company.Id)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Company>(loaded as Company, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.Id, CoreMatchers.`is`(company.Id))
        MatcherAssert.assertThat(loaded.rating, CoreMatchers.`is`(company.rating))
        MatcherAssert.assertThat(loaded.address, CoreMatchers.`is`(company.address))
        MatcherAssert.assertThat(loaded.name, CoreMatchers.`is`(company.name))
    }


    @Test
    fun deleteUserAndGet() = runBlockingTest {
        // Given a group inserted
        val company = Company(1,"addis","meles","henokaddis@gmail.com")

        // When deleting a user by id
        database.companyDao().DeleteCompany(company.Id.toLong())
        // THEN - The object returned is null
        val getCompany = database.companyDao().getCompany(company.Id)
        MatcherAssert.assertThat(getCompany, CoreMatchers.nullValue())
    }


    @Test
    fun getAllCompaniys() = runBlockingTest {
        val company = Company(1,"addis","meles","henokaddis@gmail.com")
        

        val result = database.companyDao().getCompany(company.Id)

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}

